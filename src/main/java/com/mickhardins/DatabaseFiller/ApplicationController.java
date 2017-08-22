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
import com.mickhardins.Deserializer.model.MTGSetMapped;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Crea un database e riserializza ogni set singolarmente dopo averlo GZippato
 */

public class ApplicationController {

    private static final String WORKING_DIR = System.getProperty("user.dir") + File.separator;
    public static final String INPUT_FILES_DIR = WORKING_DIR + "input_files" + File.separator;
    public static final String OUTPUT_DIR = WORKING_DIR + "output_files" + File.separator;
    public static final String SERIALIZED_SET_DIR = OUTPUT_DIR + "corrected_sets" + File.separator;
    public static final String COMPRESSED_SET_DIR = OUTPUT_DIR + "zipped_sets" + File.separator;
    public static final String MISSING_COLLECTORS_NUMBER_FOLDER = INPUT_FILES_DIR + "cnumber_txt_files" + File.separator;

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
                        //MTGCardLegalities legalities = card.getLegalities();



                        MTGArtistDAO.createIfNotExists(artist);
                        MTGCardForeignNamesDAO.create(names);
                        MTGColorDao.createIfNotExists(colors);
                        //MTGCardLegalitiesDAO.create(legalities);
                        //card.setMTGset(set);
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

    public static UpdateObject createUpdateObject(ChangelogAnalyzer changelogAnalyzer) throws IOException {

        if (!changelogAnalyzer.isNothingChanged()) {
            System.out.println("LOG:\tNessun cambiamento ai file dei set. Termino...");
            System.exit(0);
        }

        UpdateObject updateObject = UpdateObject.createUpdateObject();

        if (changelogAnalyzer.isAllSetsChanged()) {
            updateObject.setAllchanged(true);
        }
        else {
            List<String> changedSetCodes = changelogAnalyzer.getChangedSetCodes();
            //changedSetCodes.add("C17"); //TODO a volte mtgjson non inserisce i nuovi set aggiunti, e bisogna farlo manualmente
            //correzione per i suffissi "-x
            //changedSetCodes.add("AKH-x");
            for (String setCode : changedSetCodes) {
                if (setCode.endsWith("-x")) {
                    String setCodeCorrected = setCode.replace("-x","");
                    int setCodeIndex = changedSetCodes.indexOf(setCode);
                    changedSetCodes.remove(setCodeIndex);
                    changedSetCodes.add(setCodeIndex, setCodeCorrected);
                }
            }
            List<String> changedSetUrls = Utils.generateSetCodesUrls(changedSetCodes);
            updateObject.setUpdatedSets(Utils.fromListToArray(changedSetCodes));
            updateObject.setUpdatedSetsUrls(Utils.fromListToArray(changedSetUrls));
        }

        updateObject.setVersion(Utils.readDatabaseVersion() + 1);
        return updateObject;

    }

    /**
     * Versione da usare nel testing e sviluppo
     * @param changelogAnalyzer
     * @return
     * @throws IOException
     */
    public static UpdateObject createUpdateObjectTesting(ChangelogAnalyzer changelogAnalyzer) throws IOException {

        if (!changelogAnalyzer.isNothingChanged()) {
            System.out.println("LOG:\tNessun cambiamento ai file dei set. Termino...");
            System.exit(0);
        }

        UpdateObject updateObject = UpdateObject.createUpdateObject();

        if (changelogAnalyzer.isAllSetsChanged()) {
            updateObject.setAllchanged(true);
        }
        else {
            List<String> changedSetCodes = changelogAnalyzer.getChangedSetCodes();
            //correzione per i suffissi "-x
            //changedSetCodes.add("AKH-x");
            for (String setCode : changedSetCodes) {
                if (setCode.endsWith("-x")) {
                    String setCodeCorrected = setCode.replace("-x","");
                    int setCodeIndex = changedSetCodes.indexOf(setCode);
                    changedSetCodes.remove(setCodeIndex);
                    changedSetCodes.add(setCodeIndex,setCodeCorrected);
                }
            }
            List<String> changedSetUrls = Utils.generateSetCodesUrlsTesting(changedSetCodes);
            updateObject.setUpdatedSets(Utils.fromListToArray(changedSetCodes));
            updateObject.setUpdatedSetsUrls(Utils.fromListToArray(changedSetUrls));
        }

        updateObject.setVersion(Utils.readDatabaseVersion() + 1);
        return updateObject;

    }


    public static void main(String[] args) throws IOException, SQLException, Exception {



        Utils.init();
        //Utils.downloadInputFilesFromMtgjson(INPUT_FILES_DIR);
        Deserializer deserializer = new Deserializer();
        CardProcesser cardProcesser = new CardProcesser();


        //Deserializza i files necessari
        ArrayList<DeserializedMTGSet> dSets
                = deserializer.deserializeMTGset(INPUT_FILES_DIR + Utils.SETS_JSON_FILENAME);


        //ottengo i set con le correzioni manuali
        List<MTGSetMapped> mappedSetsArr = deserializer.deserializeMTGsetMapped(INPUT_FILES_DIR + Utils.MAPPED_SETS_JSON_FILENAME);
        HashMap<String, MTGSetMapped> hashMap = cardProcesser.createSetCodeMappedSetHashMap(mappedSetsArr, dSets);

        //fa side effect su dset e hashmap
        cardProcesser.correctDeserializedMtgSets(dSets, hashMap);

        ArrayList<MTGSetMapped> mappedSetsList = (ArrayList<MTGSetMapped>)Utils.fromHashmapToList(hashMap);

        deserializer.serializeMTGSetMapped(mappedSetsList);

        cardProcesser.addMciCardNumbers(dSets);
        cardProcesser.correctMciNumbers(dSets);

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
        Utils.compressSets(SERIALIZED_SET_DIR);

        // ~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~-~~~~~~~~~

        String[] setCodes = deserializer.deserializeMTGSetCodes(INPUT_FILES_DIR + Utils.SETCODES_FILENAME);
        String[] setCodesUrls = Utils.generateSetCodesUrls(setCodes);
        String[] setCodesUrlsTesting = Utils.generateSetCodesUrlsTesting(setCodes);

        deserializer.serializeSetCodesURLs(setCodesUrls, OUTPUT_DIR);
        deserializer.serializeSetCodesURLsTesting(setCodesUrlsTesting, OUTPUT_DIR);


        System.out.println("LOG:\tDeserializzazione setCodes completata");

        // ~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~~~~~~~~-~~~~~~~~~~~-~~~~~~~~~

        MTGJSONChangelog changelog = deserializer.deserializeChangelog(INPUT_FILES_DIR + Utils.CHANGELOG_JSON_FILENAME);
        ChangelogAnalyzer changelogAnalyzer = new ChangelogAnalyzer(changelog);
        UpdateObject updateObject = createUpdateObject(changelogAnalyzer);
        UpdateObject updateObjectTesting = createUpdateObjectTesting(changelogAnalyzer);


        int databaseVersion = Utils.readDatabaseVersion();
        Utils.saveDatabaseVersion(databaseVersion + 1);
        Utils.saveDatabaseVersionTesting(databaseVersion + 1);


        deserializer.serializeUpdateObject(updateObject);
        deserializer.serializeUpdateObjectTesting(updateObjectTesting);

    }
}
