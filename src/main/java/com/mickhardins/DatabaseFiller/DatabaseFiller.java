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
import com.mickhardins.Deserializer.model.DeserializedMTGSet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Crea un database e riserializza ogni set singolarmente dopo averlo GZippato
 */



public class DatabaseFiller {

    private static final String WORKING_DIR = System.getProperty("user.dir") + File.separator;
    public static final String INPUT_JSON_DIR = WORKING_DIR + "input_json" + File.separator;
    public static final String SERIALIZED_SET_DIR = WORKING_DIR + "serialized_sets" + File.separator;


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
                        MTGArtistDAO.update(artist);

                    }


                }

                return null;

            }
        });
        connection.close();
    }

    /**
     * Creates a new folder, if not already present
     * @param path the path where the folder will be created
     */
    private static void createFolder(String path) {
        File directory = new File(path);
        if( !directory.exists())
            directory.mkdirs();
    }

    private static void createDirectories() {
        createFolder(INPUT_JSON_DIR);
        createFolder(SERIALIZED_SET_DIR);
    }



    public static void main (String[] args) throws IOException,SQLException {

        createDirectories();


        //Deserializza dal file Allsets
        ArrayList<DeserializedMTGSet> dsets = Deserializer.deserializeMTGset(INPUT_JSON_DIR + "AllSetsArray-x.json");

        //Converte i foreignNames in oggetti
        CardProcessing.foreignNamesConverter(dsets);

        //Converte l'oggetto Rulings in stringa
        CardProcessing.rulingstoString(dsets);

        //Converte gli Artisti in oggetti
        CardProcessing.artistConverter(dsets);


        CardProcessing.colorObjectAdder(dsets);

        ArrayList<MTGSet> sets = CardProcessing.fillingPreparator(dsets);



        Deserializer.serialize(sets);
        System.out.println("Write successfull!");

        String[] arr = Deserializer.deserializeMTGSetCode(INPUT_JSON_DIR + "SetCodes.json");
        Deserializer.setCodestoURL(arr);
        Deserializer.serializeSetCodesURLs(arr, SERIALIZED_SET_DIR);

        System.out.println("finita deserializzazione setcodes");








        /* Inserisce gli oggetti nel database SQlite chiamando il metodo databaseFiller
        databaseFiller(sets);

        */

        /*ArrayList<MTGCard> cards = (ArrayList<MTGCard>)sets.get(0).getCards();
        MTGCard card =cards.get(10);
        System.out.println(card.getName()+"\n"+ card.getColors().toString());

        System.out.println("godo");*/



    }
}
