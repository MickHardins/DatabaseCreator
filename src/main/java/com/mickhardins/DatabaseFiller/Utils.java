package com.mickhardins.DatabaseFiller;

import com.mickhardins.DatabaseFiller.model.MTGSet;
import com.mickhardins.Deserializer.model.MTGSetMapped;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

/**
 * Created by mick on 22/04/17.
 */
public class Utils {

    static String MTG_JSON_DOMAIN = "http://mtgjson.com/json/";
    static String SETS_JSON_FILENAME = "AllSetsArray-x.json";
    static String SETCODES_FILENAME = "SetCodes.json";
    static String CHANGELOG_JSON_FILENAME = "changelog.json";
    static String MAPPED_SETS_JSON_FILENAME = "SetMapping.json";
    static String FILE_DOWNLOAD_URL_PART1 = "https://github.com/MickHardins/mickhardins.github.io/raw/master/files/zipped_sets/";
    static String FILE_DOWNLOAD_URL_PART2 = ".json.gzip";

    /**
     * Url dei file mirror che usiamo per testare
     */
    static String FILE_DOWNLOAD_TEST_URL_PART1 = "https://github.com/MickHardins/mickhardins.github.io/raw/master/files_testing/zipped_sets/";
    static String FILE_DOWNLOAD_TEST_URL_PART2 = ".json.gzip";




    /**
     * Creates a new folder, if not already present
     * @param path the path where the folder will be created
     */
    private static void createFolder(String path) {
        File directory = new File(path);
        if(!directory.exists())
            directory.mkdirs();
    }

    public static boolean exists(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    private static void createDirectories() {
        Utils.createFolder(ApplicationController.INPUT_FILES_DIR);
        Utils.createFolder(ApplicationController.OUTPUT_DIR);
        Utils.createFolder(ApplicationController.COMPRESSED_SET_DIR);
        Utils.createFolder(ApplicationController.SERIALIZED_SET_DIR);
    }

    static void init() throws IOException {
        createDirectories();
        File databaseVersion = new File(ApplicationController.OUTPUT_DIR + "DatabaseVersionNumber.txt");
        if (!databaseVersion.exists() && !databaseVersion.isDirectory()) {
            saveDatabaseVersion(0);
        }
    }

    private static File[] getSampleFileList(String folderPath, String fileExtension) {

        File directory = new File(folderPath);
        if(!directory.isDirectory())
            return new File[]{};
        File[] result = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String filename = pathname.getName().toLowerCase();
                String extension = fileExtension;
                boolean match = extension.equalsIgnoreCase(fileExtension);
                return (!filename.startsWith(".") && match && !pathname.isHidden() && !pathname.isDirectory());
            }
        });
        return result;
    }

    /**
     * Compress a list of Json,
     * @param sets a List of MTGSets,
     * @throws IOException
     */
    public static void compressSets_old(ArrayList<MTGSet> sets) throws IOException {
        //todo leggere solo la lista di filepresenti nellaserialized_dir e fare un check per escludere setURls.json
        for (MTGSet set : sets) {

            byte[] buffer = new byte[1024];
            String setCode = set.getCode();
            String sourcepath = "";
            String destination_path = "";

            if (set.getCode().equals("CON")) {

                sourcepath = ApplicationController.SERIALIZED_SET_DIR + "_" + setCode + ".json";
                destination_path = ApplicationController.SERIALIZED_SET_DIR + "_" + setCode + ".json.gzip";

            }
            else {

                sourcepath = ApplicationController.SERIALIZED_SET_DIR  + setCode + ".json";
                destination_path = ApplicationController.SERIALIZED_SET_DIR  + setCode + ".json.gzip";

            }
            FileOutputStream fileOutputStream = new FileOutputStream(destination_path);
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
            FileInputStream fileInputStream = new FileInputStream(sourcepath);
            int bytes_read;
            while ((bytes_read = fileInputStream.read(buffer)) > 0) {
                gzipOutputStream.write(buffer, 0, bytes_read);
            }
            fileInputStream.close();
            gzipOutputStream.finish();
            gzipOutputStream.close();
            System.out.println(set.getCode() + " successfully compressed!");
        }


    }

    static void compressSets(String path) throws IOException {

        File[] jsonFiles = getSampleFileList(path, ".json");
        byte[] buffer = new byte[1024];

        for (File json : jsonFiles) {
            String setCode = json.getName().substring(0,json.getName().lastIndexOf('.'));
            String destination_path = ApplicationController.COMPRESSED_SET_DIR + setCode + ".json.gzip";
            FileOutputStream fileOutputStream = new FileOutputStream(destination_path);
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
            FileInputStream fileInputStream = new FileInputStream(json.getPath()); //todo Check
            int bytes_read;
            while ((bytes_read = fileInputStream.read(buffer)) > 0) {
                gzipOutputStream.write(buffer, 0, bytes_read);
            }
            fileInputStream.close();
            gzipOutputStream.finish();
            gzipOutputStream.close();
        }
        System.out.println("LOG:\tCompletata compressione dei file json");
    }

    /**
     * A partire dai setCodes genera un array contenente gli Url dei set
     * @param setCodes
     * @return
     */
    public static List<String> generateSetCodesUrls(List<String> setCodes) {
        ArrayList<String> setUrls = new ArrayList<>(setCodes.size());
        for (String setCode : setCodes) {

            if (setCode.equals("CON")) {
                setCode = "_CON";
            }

            String part1 = FILE_DOWNLOAD_URL_PART1;
            String part2 = FILE_DOWNLOAD_URL_PART2;
            String url = part1 + setCode + part2;
            setUrls.add(url);
        }
        return setUrls;
    }

    /**
     * Genera la lista di url per i file dei set da usare nei test
     * @param setCodes
     * @return
     */
    public static List<String> generateSetCodesUrlsTesting(List<String> setCodes) {
        ArrayList<String> setUrls = new ArrayList<>(setCodes.size());
        for (String setCode : setCodes) {

            if (setCode.equals("CON")) {
                setCode = "_CON";
            }

            String part1 = FILE_DOWNLOAD_TEST_URL_PART1;
            String part2 = FILE_DOWNLOAD_TEST_URL_PART2;
            String url = part1 + setCode + part2;
            setUrls.add(url);
        }
        return setUrls;
    }

    public static String[] generateSetCodesUrls(String[] setCodes) {

        String[] setUrls = new String[setCodes.length];
        for (int i = 0; i < setCodes.length; i++) {

            if (setCodes[i].equals("CON")) {
                setCodes[i] = "_CON";
            }
            String part1 = FILE_DOWNLOAD_URL_PART1;
            String part2 = FILE_DOWNLOAD_URL_PART2;
            String url = part1 + setCodes[i] + part2;
            setUrls[i] = url;
        }
        return setUrls;
    }
    /**
     * Genera la lista di url per i file dei set da usare nei test
     * @param setCodes
     * @return
     */
    public static String[] generateSetCodesUrlsTesting(String[] setCodes) {

        String[] setUrls = new String[setCodes.length];
        for (int i = 0; i < setCodes.length; i++) {

            if (setCodes[i].equals("CON")) {
                setCodes[i] = "_CON";
            }
            String part1 = FILE_DOWNLOAD_TEST_URL_PART1;
            String part2 = FILE_DOWNLOAD_TEST_URL_PART2;
            String url = part1 + setCodes[i] + part2;
            setUrls[i] = url;
        }
        return setUrls;
    }

    public static String[] fromListToArray(List<String> list) {
        String[] result = new String[list.size()];
        result = list.toArray(result);
        return result;

    }


    public static void saveDatabaseVersion(int version) throws IOException {
        String versionString = Integer.toString(version);
        FileWriter writer2 = new FileWriter(ApplicationController.OUTPUT_DIR + "DatabaseVersionNumber.txt");
        writer2.write(versionString);
        writer2.close();
        System.out.println("LOG:\tVersione del database aggiornata");

    }

    /**
     * Crea il file database version da usare nei test di sviluppo
     * @param version
     * @throws IOException
     */
    public static void saveDatabaseVersionTesting(int version) throws IOException {
        String versionString = Integer.toString(version);
        FileWriter writer2 = new FileWriter(ApplicationController.OUTPUT_DIR + "DatabaseVersionNumberTesting.txt");
        writer2.write(versionString);
        writer2.close();
        System.out.println("LOG:\tVersione del database aggiornata");

    }

    public static int readDatabaseVersion() throws IOException {

        Path filePath = Paths.get(ApplicationController.OUTPUT_DIR + "DatabaseVersionNumber.txt");
        Scanner scanner = new Scanner(filePath);
        int version = -1;
        while (scanner.hasNextInt()) {
            version = scanner.nextInt();
        }
        return version;
    }

    private static List<String> getInputFilesNames() {
        ArrayList<String> result = new ArrayList<String>(3);
        result.add(SETS_JSON_FILENAME);
        result.add(SETCODES_FILENAME);
        result.add(CHANGELOG_JSON_FILENAME);
        return result;
    }

    public static void downloadInputFilesFromMtgjson(String destPath) {

        List<String> fileNames = getInputFilesNames();
        try {
            System.out.println("LOG:\tDownload dei file necessari in corso......");
            for (String fileName : fileNames) {
                URL fileUrl = new URL(MTG_JSON_DOMAIN + fileName);
                InputStream in = fileUrl.openStream();
                Files.copy(in, Paths.get(destPath + fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("LOG:\tDownload del file " + fileName + " completato");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOG:\tErrore, termino...");
            System.exit(-1);
        }

    }

    public static List<MTGSetMapped> fromHashmapToList(HashMap<String, MTGSetMapped> hashMap) {
        ArrayList<MTGSetMapped> result = new ArrayList<>(hashMap.size());
        for (String key : hashMap.keySet()) {
            result.add(hashMap.get(key));
        }
        return result;
    }

    /**
     * Ritorna un'hashmap con associazione nomecarta - mcinumber
     * ATTENZIONE!! Le terre conterranno solo il primo numero, in caso di + versioni occorre
     * tenere traccia del numero attuale nella funzione di correzione
     * @param setCode
     * @return
     * @throws Exception
     */
    public static HashMap<String,String> generateCardNumbersHashmap(String setCode) throws Exception {
        HashMap<String, String> hashMap = null;
        if (Utils.exists(ApplicationController.MISSING_COLLECTORS_NUMBER_FOLDER + setCode + ".txt")) {
            hashMap = new HashMap<>();
            File txtFile = new File(ApplicationController.MISSING_COLLECTORS_NUMBER_FOLDER + setCode + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(txtFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\\t");
                String cardNumber = splitted[0];
                String cardName = splitted[1];
                if (hashMap.containsKey(cardName)) {
                    continue;
                }
                hashMap.put(cardName, cardNumber);
            }
        }
        return hashMap;
    }



}
