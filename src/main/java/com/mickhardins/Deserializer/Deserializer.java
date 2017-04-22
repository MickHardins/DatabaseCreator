package com.mickhardins.Deserializer;

import com.google.gson.Gson;
import com.mickhardins.DatabaseFiller.ApplicationController;
import com.mickhardins.DatabaseFiller.model.MTGSet;
import com.mickhardins.DatabaseFiller.model.UpdateObject;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;
import com.mickhardins.Deserializer.model.DeserializedMTGLegalities;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;


/**
 * Created by Mick on 19/12/2014.
 */
public class Deserializer {

    public Deserializer() {

    }

    /**
     *
     * @param path path del file AllSetsArray-x scaricabile da mtgJson.com
     * @return  an arrayList of @DeserializedMTGSet
     * @throws IOException
     */
    public static ArrayList<DeserializedMTGSet> deserializeMTGset(String path) throws IOException {

        InputStream inputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        DeserializedMTGSet[] mtgSets = gson.fromJson(reader,DeserializedMTGSet[].class);

        //converto l'array in arraylist, forse inutilmente//
        ArrayList<DeserializedMTGSet> sets = new ArrayList<DeserializedMTGSet>(Arrays.asList(mtgSets));
        System.out.println("Deserializzati MTGSets");
        return sets;
    }

    /**
     * Serializza ogni set escludendo i campi id e GZippa i file
     * @param sets
     * @throws IOException
     */
    public static void serializeAndCompress(ArrayList<MTGSet> sets) throws IOException {

        for (MTGSet set_x : sets) {

            String setCode = set_x.getCode();
            Gson gson = new Gson();
            String json = gson.toJson(set_x);
            FileWriter writer;

            if (set_x.getCode().equals("CON")) {
                writer = new FileWriter(ApplicationController.SERIALIZED_SET_DIR + "_"+setCode+".json");
            }
            else {
                writer = new FileWriter(ApplicationController.SERIALIZED_SET_DIR + setCode + ".json");
            }

            writer.write(json);
            writer.close();
        }

    }

    /**
     * Compress a list of Json,
     * @param sets a List of MTGSets,
     * @throws IOException
     */
    public static void compressSets(ArrayList<MTGSet> sets) throws IOException {
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

    /**
     * Deserializza la lista dei set di mtgjson.com
     * @param path path del json da deserializzare
     * @return array di set codes
     * @throws IOException
     */
    public static String[] deserializeMTGSetCodes(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        String[] setCodes;
        setCodes = gson.fromJson(reader,String[].class);
        System.out.println("Deserializzati set codes");
        return setCodes;
    }

    /**
     * A paertire dai setCodes genera un array contenente gli Url dei set
     * @param setCodes
     * @return
     */
    public static String[] setCodesToURL(String[] setCodes) {
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

    /**
     * Serializza in formato json l'array contenente la lista di urls dei set di mtgjson
     * @param setUrls
     * @param outPath
     * @throws IOException
     */
    public static void serializeSetCodesURLs(String[] setUrls, String outPath)throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(setUrls);
        FileWriter writer = new FileWriter(outPath + "SetURLs.json");
        writer.write(json);
        writer.close();
    }

    public static void serializeUpdateObject(UpdateObject updateObject)throws IOException{

        Gson gson = new Gson();
        String json = gson.toJson(updateObject);
        FileWriter writer = new FileWriter("C:/Dati/Mick/MTGAPP/CorrectedSets/UpdateObject.json");
        writer.write(json);
        writer.close();
        int version = updateObject.getVersion();
        String versionString = Integer.toString(version);
        FileWriter writer2 = new FileWriter("C:/Dati/Mick/MTGAPP/CorrectedSets/DatabaseVersionNumber.txt");
        writer2.write(versionString);
        writer2.close();

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



}




