package com.mickhardins.DatabaseFiller;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mickhardins.DatabaseFiller.model.*;
import com.mickhardins.Deserializer.CardProcesser;
import com.mickhardins.Deserializer.Deserializer;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;
import com.mickhardins.Deserializer.model.MTGJSONChangelog;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Crea un database e riserializza ogni set singolarmente dopo averlo GZippato
 */



public class ApplicationController {

    private static final String WORKING_DIR = System.getProperty("user.dir") + File.separator;
    public static final String INPUT_JSON_DIR = WORKING_DIR + "input_json" + File.separator;
    public static final String OUTPUT_DIR = WORKING_DIR + "output_files" + File.separator;
    public static final String SERIALIZED_SET_DIR = OUTPUT_DIR + "corrected_sets" + File.separator;
    public static final String COMPRESSED_SET_DIR = OUTPUT_DIR + "zipped_sets" + File.separator;




    public static void databaseFiller( final ArrayList<MTGSet> sets) throws SQLException {
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



    private static void createDirectories() {
        Utils.createFolder(INPUT_JSON_DIR);
        Utils.createFolder(OUTPUT_DIR);
        Utils.createFolder(COMPRESSED_SET_DIR);
        Utils.createFolder(SERIALIZED_SET_DIR);
    }

    public static UpdateObject createUpdateObject(int version){
        UpdateObject updateObject = new UpdateObject(version);

        //updateObject.setVersion(4);

        /*settare a true se sono stati updatati tutti i set*/
        updateObject.setAllchanged(true);

        if(updateObject.isAllchanged()){

            return updateObject;
        }
        else{
            ArrayList<String> setCodeArrayList = new ArrayList<>();
            /*inserire i codici*/
            setCodeArrayList.add("KTK");
            setCodeArrayList.add("SOM");
            setCodeArrayList.add("pCEL");



            /*---------------*/

            String[] setCodeArray = new String[setCodeArrayList.size()];
            setCodeArray = setCodeArrayList.toArray(setCodeArray);

            updateObject.setUpdatedSets(setCodeArray);

            String[] setCodeCOPY = setCodeArray.clone();


            String[] updatedSetUrls = Deserializer.setCodesToURL(setCodeCOPY); //fa side effect cristodio

            updateObject.setUpdatedSetsUrls(updatedSetUrls);


            return updateObject;

        }
    }



    public static void main (String[] args) throws IOException,SQLException {

        createDirectories();
        Deserializer deserializer = new Deserializer();
        CardProcesser cardProcesser = new CardProcesser();


        //Deserializza i files necessari
        ArrayList<DeserializedMTGSet> dSets
                = deserializer.deserializeMTGset(INPUT_JSON_DIR + "AllSetsArray-x.json");

        //Converte i foreignNames in oggetti
        cardProcesser.foreignNamesConverter(dSets);

        //Converte l'oggetto Rulings in stringa
        cardProcesser.rulingstoString(dSets);

        //Converte gli Artisti in oggetti
        cardProcesser.artistConverter(dSets);

        cardProcesser.addSetCodeAndNameToCards(dSets);

        cardProcesser.colorObjectAdder(dSets);

        ArrayList<MTGSet> sets = cardProcesser.transportAllSets(dSets);

        deserializer.serializeMTGSets(sets);
        System.out.println("LOG:\tSerializzati tutti i set");
        Utils.compressSets(ApplicationController.COMPRESSED_SET_DIR);

        // ~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~-~~~~~~~~~

        String[] setCodes = deserializer.deserializeMTGSetCodes(INPUT_JSON_DIR + "SetCodes.json");
        String[] setCodesUrls = Utils.generateSetCodesUrls(setCodes);
        deserializer.serializeSetCodesURLs(setCodes, OUTPUT_DIR);
        System.out.println("LOG:\t finita deserializzazione setcodes");

        // ~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~-~~~~~~~~~

        MTGJSONChangelog changelog = deserializer.deserializeChangelog(INPUT_JSON_DIR + "changelog.json");
        ChangelogAnalyzer changelogAnalyzer = new ChangelogAnalyzer(changelog);

        if(changelogAnalyzer.isNothingChanged()) {
            System.exit(0);
        }

        






    }
}
