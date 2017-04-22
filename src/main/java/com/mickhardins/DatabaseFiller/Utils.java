package com.mickhardins.DatabaseFiller;

import com.mickhardins.DatabaseFiller.model.MTGSet;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

/**
 * Created by mick on 22/04/17.
 */
public class Utils {


    /**
     * Creates a new folder, if not already present
     * @param path the path where the folder will be created
     */
    protected static void createFolder(String path) {
        File directory = new File(path);
        if( !directory.exists())
            directory.mkdirs();
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

    public static void compressSets(String path) throws IOException {

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
            System.out.println("LOG\t" + setCode + " successfully compressed!");
        }
    }

    /**
     * A partire dai setCodes genera un array contenente gli Url dei set
     * @param setCodes
     * @return
     */
    public static String[] generateSetCodesUrls(String[] setCodes) {
        String[] setUrls = new String[setCodes.length];
        for (int i = 0; i < setCodes.length; i++) {

            if (setCodes[i].equals("CON")) {
                setCodes[i] = "_CON";
            }

            String part1 = "https://sites.google.com/site/mtgrecall/sets/";
            String part2 = ".json.gzip?attredirects=0&d=1";
            String url = part1 + setCodes[i] + part2;
            setUrls[i] = url;
        }
        return setUrls;
    }

    public static void saveDatabaseVersion(int version) throws IOException {
        String versionString = Integer.toString(version);
        FileWriter writer2 = new FileWriter(ApplicationController.OUTPUT_DIR + "DatabaseVersionNumber.txt");
        writer2.write(versionString);
        writer2.close();

    }


}
