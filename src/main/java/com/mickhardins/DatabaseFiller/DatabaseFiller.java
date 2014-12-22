package com.mickhardins.DatabaseFiller;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mickhardins.DatabaseFiller.model.*;
import com.mickhardins.Deserializer.CardProcessing;
import com.mickhardins.Deserializer.Deserializer;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Mick on 21/12/2014.
 */

public class DatabaseFiller
{

    public static void databaseFiller( final ArrayList<MTGSet> sets) throws SQLException
    {
        final ConnectionSource connection = new JdbcConnectionSource("jdbc:sqlite:mydatabase.db");
        final Dao<MTGSet,Long> MTGSetDAO = DaoManager.createDao(connection, MTGSet.class);
        final Dao<MTGCard,Long> MTGCardDAO = DaoManager.createDao(connection,MTGCard.class);
        final Dao<MTGCardLegalities, Long> MTGCardLegalitiesDAO = DaoManager.createDao(connection, MTGCardLegalities.class);
        final Dao<MTGArtist, Long> MTGArtistDAO = DaoManager.createDao(connection,MTGArtist.class);
        final Dao<MTGColors, Integer> MTGColorDao = DaoManager.createDao(connection,MTGColors.class);
        final Dao<MTGCardForeignNames, Long> MTGCardForeignNamesDAO = DaoManager.createDao(connection,MTGCardForeignNames.class);

        TableUtils.createTable(connection,MTGSet.class);
        TableUtils.createTable(connection,MTGCard.class);
        TableUtils.createTable(connection,MTGCardLegalities.class);
        TableUtils.createTable(connection,MTGArtist.class);
        TableUtils.createTable(connection,MTGColors.class);
        TableUtils.createTable(connection,MTGCardForeignNames.class);



        TransactionManager.callInTransaction(connection, new Callable<Void>() {
            public Void call() throws Exception {

                for(MTGSet set : sets) {
                    ArrayList<MTGCard> cards = (ArrayList<MTGCard>)set.getCards();

                    MTGSetDAO.create(set);


                    for( MTGCard card : cards) {

                        MTGArtist artist = card.getArtist();
                        MTGCardForeignNames names = card.getForeignNames();
                        MTGColors  colors = card.getColors();
                        MTGCardLegalities legalities = card.getLegalities();



                        MTGArtistDAO.createIfNotExists(artist);
                        MTGCardForeignNamesDAO.create(names);
                        MTGColorDao.createIfNotExists(colors);
                        MTGCardLegalitiesDAO.create(legalities);
                        card.setMTGset(set);
                        MTGCardDAO.create(card);
                        MTGCardDAO.update(card);

                    }


                }

                return null;

            }
        });
        connection.close();
    }



    public static void main (String[] args) throws IOException,SQLException
    {

        ArrayList<DeserializedMTGSet> dsets = Deserializer.deserialize("C:/Dati/Mick/MTGAPP/AllSetsArray-x-formatted.json");
        CardProcessing.foreignNamesConverter(dsets);
        CardProcessing.rulingstoString(dsets);

        CardProcessing.artistConverter(dsets);
        Map<Integer,MTGColors> map = CardProcessing.colorObjectAdder(dsets);

        ArrayList<MTGSet> sets = CardProcessing.fillingPreparator(dsets);

        databaseFiller(sets);

















        /*ArrayList<MTGCard> cards = (ArrayList<MTGCard>)sets.get(0).getCards();
        MTGCard card =cards.get(10);
        System.out.println(card.getName()+"\n"+ card.getColors().toString());

        System.out.println("godo");*/



    }
}
