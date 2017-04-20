package com.mickhardins.Deserializer;

import com.google.gson.Gson;
import com.mickhardins.DatabaseFiller.DatabaseFiller;
import com.mickhardins.DatabaseFiller.model.MTGSet;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;


/**
 * Created by Mick on 19/12/2014.
 */
public class Deserializer
{

    public static ArrayList<DeserializedMTGSet> deserializeMTGset(String path) throws IOException
    {

        //"C:/Dati/Mick/MTGAPP/AllSetsArray-x-formatted.json"

        InputStream inputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();

        DeserializedMTGSet[] arr = gson.fromJson(reader,DeserializedMTGSet[].class);

        //converto l'array in arraylist, forse inutilmente//
        ArrayList<DeserializedMTGSet> sets = new ArrayList<DeserializedMTGSet>(Arrays.asList(arr));

        // Scorri ogni set //
        for(DeserializedMTGSet set_x : sets) {

            /*ottieni tutte le carte del set*/

            ArrayList<DeserializedMTGCard> cards = set_x.getCards();

            //Per ogni carta del set aggiorna i campi setCode,setName, work_legalities
            for( DeserializedMTGCard card_x : cards) {

                card_x.setSetName(set_x.getName());
                card_x.setSetCode(set_x.getCode());
                CardProcessing.legalityArrToObject(card_x);

            }
        }
        System.out.println("Finita conversione legalities da hash a oggetto");
        //aggiunta della pauper legalities
        CardProcessing.pauperLegalitiesAdder(sets);

        System.out.println("Finita aggiunta di pauper legalities");

        return sets;
    }

    public static void serialize(ArrayList<MTGSet> sets) throws IOException
    {
        /* Serializza ogni set escludendo i campi id e GZippa i file
         */

        for(MTGSet set_x : sets){


            String setCode = set_x.getCode();
            Gson gson = new Gson();
            String json = gson.toJson(set_x);
            FileWriter writer;
            if(set_x.getCode().equals("CON")){
                writer = new FileWriter(DatabaseFiller.SERIALIZED_SET_DIR + "_"+setCode+".json");
            }
            else {
                writer = new FileWriter(DatabaseFiller.SERIALIZED_SET_DIR + setCode + ".json");
            }

            writer.write(json);
            writer.close();
            compressSets(set_x);
        }


        /*for(MTGSet set_x : sets) {
            File f = new File("C:/Dati/Mick/MTGAPP/CorrectedSets/" + set_x.getCode() + ".json");
            if (!f.exists()) {
                System.out.println(set_x.getCode());
            }

        }*/
    }

    public static void compressSets(MTGSet set) throws IOException
    {
        byte[] buffer = new byte[1024];

        String setCode = set.getCode();
        String sourcepath = "";
        String destination_path = "";
        if(set.getCode().equals("CON")){

            sourcepath = DatabaseFiller.SERIALIZED_SET_DIR + "_" + setCode + ".json";
            destination_path = DatabaseFiller.SERIALIZED_SET_DIR + "_" + setCode + ".json.gzip";

        }
        else {

            sourcepath = DatabaseFiller.SERIALIZED_SET_DIR  + setCode + ".json";
            destination_path = DatabaseFiller.SERIALIZED_SET_DIR  + setCode + ".json.gzip";

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
        System.out.println("The file was compressed successfully!");

    }

    public static String[] deserializeMTGSetCode(String path) throws IOException
    {
        InputStream inputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        String[] setCodes;
        setCodes = gson.fromJson(reader,String[].class);
        System.out.println("Deserializzati sets");
        return setCodes;
    }

    public static String[] setCodestoURL(String[] arr)
    {
        for (int i = 0; i<arr.length;i++){
            if(arr[i].equals("CON")){
                arr[i] = "_CON";
            }
            String part1 = "https://sites.google.com/site/mtgrecall/sets/";
            String part2 = ".json.gzip?attredirects=0&d=1";
            String result = part1 + arr[i] + part2;
            arr[i]=result;
        }
        return arr;
    }

    public static void serializeSetCodesURLs(String[] arr, String outPath)throws IOException
    {
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        FileWriter writer = new FileWriter(outPath + "SetURLs.json");
        writer.write(json);
        writer.close();
    }



}




