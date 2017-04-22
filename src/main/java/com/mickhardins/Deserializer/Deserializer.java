package com.mickhardins.Deserializer;

import com.google.gson.Gson;
import com.mickhardins.DatabaseFiller.ApplicationController;
import com.mickhardins.DatabaseFiller.model.MTGSet;
import com.mickhardins.DatabaseFiller.model.UpdateObject;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;
import com.mickhardins.Deserializer.model.DeserializedMTGLegalities;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;
import com.mickhardins.Deserializer.model.MTGJSONChangelog;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

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
    public ArrayList<DeserializedMTGSet> deserializeMTGset(String path) throws IOException {

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
    public void serializeMTGSets(ArrayList<MTGSet> sets) throws IOException {

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
     * Deserializza la lista dei set di mtgjson.com
     * @param path path del json da deserializzare
     * @return array di set codes
     * @throws IOException
     */
    public String[] deserializeMTGSetCodes(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        String[] setCodes;
        setCodes = gson.fromJson(reader,String[].class);
        System.out.println("LOG:\tDeserializzati set codes");
        return setCodes;
    }



    /**
     * Serializza in formato json l'array contenente la lista di urls dei set di mtgjson
     * @param setUrls
     * @param outPath
     * @throws IOException
     */
    public void serializeSetCodesURLs(String[] setUrls, String outPath)throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(setUrls);
        FileWriter writer = new FileWriter(outPath + "SetURLs.json");
        writer.write(json);
        writer.close();
    }

    public void serializeUpdateObject(UpdateObject updateObject)throws IOException{

        Gson gson = new Gson();
        String json = gson.toJson(updateObject);
        FileWriter writer = new FileWriter(ApplicationController.OUTPUT_DIR + "UpdateObject.json");
        writer.write(json);
        writer.close();
    }


    /**
     * Ritorna l'ultimo oggetto changelogs, che dovrebbe essere il più recente
     * @param path
     * @return
     * @throws IOException
     */
    public MTGJSONChangelog deserializeChangelog(String path) throws IOException{
        InputStream inputStream = new FileInputStream(path);
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        MTGJSONChangelog[] changelogs;
        changelogs = gson.fromJson(reader, MTGJSONChangelog[].class);
        return changelogs[0];
    }





}




