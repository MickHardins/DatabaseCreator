package com.mickhardins.DatabaseFiller;

import com.mickhardins.DatabaseFiller.model.MTGArtist;
import com.mickhardins.Deserializer.CardProcessing;
import com.mickhardins.Deserializer.Deserializer;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mick on 21/12/2014.
 */

public class DatabaseFiller
{



    public static void main (String[] args) throws IOException
    {

        ArrayList<DeserializedMTGSet> dsets = Deserializer.deserialize("C:/Dati/Mick/MTGAPP/AllSetsArray-x-formatted.json");
        CardProcessing.foreignNamesConverter(dsets);
        CardProcessing.rulingstoString(dsets);

        CardProcessing.artistConverter(dsets);
        System.out.println("godo");



    }
}
