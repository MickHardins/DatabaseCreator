package com.Deserializer;

import com.Deserializer.model.DeserializedMTGCard;
import com.Deserializer.model.DeserializedMTGCardLegalities;
import com.Deserializer.model.DeserializedMTGSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mick on 19/12/2014.
 */
public class CardProcessing
{

    private static boolean isInTheBanList(DeserializedMTGCard c)
    {
        ArrayList<String> pauperban = new ArrayList<String>(7);
        pauperban.add("Cloudpost");
        pauperban.add("Cranial Plating");
        pauperban.add("Empty the Warrens");
        pauperban.add("Frantic Search");
        pauperban.add("Grapeshot");
        pauperban.add("Invigorate");
        pauperban.add("Temporal Fissure");

        return pauperban.contains(c.getName());

    }

    private static boolean hasBeenPrintedCommon(ArrayList<DeserializedMTGSet> sets, DeserializedMTGCard c, ArrayList<String> printings)
    {

        boolean result = false;
        for(DeserializedMTGSet set_x : sets){
            if(printings.contains(set_x.getName())) {
                ArrayList<DeserializedMTGCard> cards = set_x.getCards();
                //se l'la carta è nel set cerca la rarità
                if(cards.contains(c)) {

                    DeserializedMTGCard candidate_card =  cards.get(cards.indexOf(c));   //dammi l'elemento che ha lo stesso nome di c
                    if(candidate_card.getRarity().equals("Common")) {

                        return true;
                    }
                }
            }

        }
        return result;

    }

    private static void pauperLegalitiesAdder(ArrayList<DeserializedMTGSet> sets)
    {

        for (DeserializedMTGSet set_x : sets) {

            /* ottieni tutte le carte del set */

            ArrayList<DeserializedMTGCard> cards = set_x.getCards();

            /* Per ogni carta del set aggiungi la pauper legalities */

            for (DeserializedMTGCard c : cards) {

                /* se è un set che nn ha legalities saltala*/
                if(c.getLegalities()==null) {

                    continue;
                }
                /* se è in banList scrivi banned */
                if(isInTheBanList(c)) {
                    c.getWork_legalities().setPauper("Banned");
                }

                /* se non lo è controlla la rarità */

                else {

                    if(c.getRarity().equals("Common")) {

                        /* la carta è comune--tutto ok */

                        c.getWork_legalities().setPauper("Legal");
                    }
                    else {
                        ArrayList<String> printings = c.getPrintings();
                        /* s è stata stampata comune allora è legale */
                        if(hasBeenPrintedCommon(sets,c,printings)) {
                            c.getWork_legalities().setPauper("Legal");
                        }
                    }
                }
            }
        }
    }

    private static void hashLegalitiesToObject(DeserializedMTGCard c )
    {

        HashMap<String,String> map = c.getLegalities();
        if(map == (null)){
            return;
        }

        DeserializedMTGCardLegalities updated_legalities = new DeserializedMTGCardLegalities();

        //if (map.entrySet().equals(null)){System.out.print(c.getName() + "porcoddio");}


        for(Map.Entry<String, String> entry : map.entrySet()) {
            /*valuta il nome della chiave e inserisci il valore associato nel giusto campo dell'oggetto MTGCardLegalities*/
            String chiave = entry.getKey();
            if(chiave.contains("Block")) {

                updated_legalities.setBlock(entry.getValue());
                continue;

            }
            switch(chiave) {
                case "Standard":
                    updated_legalities.setStandard(entry.getValue());
                    break;
                case "Modern":
                    updated_legalities.setModern(entry.getValue());
                    break;
                case "Legacy":
                    updated_legalities.setLegacy(entry.getValue());
                    break;
                case "Vintage":
                    updated_legalities.setVintage(entry.getValue());
                    break;
                case "Freeform":
                    updated_legalities.setFreeform(entry.getValue());
                    break;
                case "Prismatic":
                    updated_legalities.setPrismatic(entry.getValue());
                    break;
                case "Tribal Wars Legacy":
                    updated_legalities.setTribal_wars_legacy(entry.getValue());
                    break;
                case "Tribal Wars Standard":
                    updated_legalities.setTribal_wars_standard(entry.getValue());
                    break;
                case "Singleton 100":
                    updated_legalities.setSingleton100(entry.getValue());
                    break;
                case "Commander":
                    updated_legalities.setCommander(entry.getValue());
                    break;

            }

        }

        c.setWork_legalities(updated_legalities);

    }
}
