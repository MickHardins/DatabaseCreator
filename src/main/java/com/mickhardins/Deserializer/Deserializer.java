package com.mickhardins.Deserializer;

import com.google.gson.Gson;
import com.mickhardins.DatabaseFiller.model.MTGSet;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Mick on 19/12/2014.
 */
public class Deserializer
{

    public static ArrayList<DeserializedMTGSet> deserialize(String path) throws IOException
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
                CardProcessing.hashLegalitiesToObject(card_x);

            }
        }
        System.out.println("Finita conversione legalities da hash a oggetto");
        CardProcessing.pauperLegalitiesAdder(sets);
        System.out.println("Finita aggiunta di pauper legalities");

        return sets;
    }




}




