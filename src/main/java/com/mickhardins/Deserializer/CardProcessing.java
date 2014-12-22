package com.mickhardins.Deserializer;

import com.mickhardins.DatabaseFiller.model.*;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;
import com.mickhardins.Deserializer.model.DeserializedMTGCardForeignName;
import com.mickhardins.Deserializer.model.DeserializedMTGCardRuling;
import com.mickhardins.Deserializer.model.DeserializedMTGSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mick on 19/12/2014.
 */
public class CardProcessing
{

    //tested
    public static boolean isInTheBanList(DeserializedMTGCard c)
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


    //tested
    public static boolean hasBeenPrintedCommon(ArrayList<DeserializedMTGSet> sets, DeserializedMTGCard c, ArrayList<String> printings)
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

    //tested
    public static void pauperLegalitiesAdder(ArrayList<DeserializedMTGSet> sets)
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

    //tested
    public static void hashLegalitiesToObject(DeserializedMTGCard c )
    {

        HashMap<String,String> map = c.getLegalities();
        if(map == (null)){
            return;
        }

        MTGCardLegalities updated_legalities = new MTGCardLegalities();

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

    //tested
    public static Map<Integer,MTGColors> colorObjectAdder(ArrayList<DeserializedMTGSet> sets)
    {
        Map<Integer, MTGColors> colorsMap = new HashMap<>();

        for (DeserializedMTGSet set : sets) {

            ArrayList<DeserializedMTGCard> cards = set.getCards();

            for (DeserializedMTGCard card : cards) {
                int colorID = MTGColors.colorID(card);
                if (!colorsMap.containsKey(colorID)) {
                    colorsMap.put(colorID, new MTGColors(colorID));
                }
                card.setWork_colors(colorsMap.get(colorID));
            }

        }
        return colorsMap;
    }

    public static ArrayList<MTGSet> fillingPreparator(ArrayList<DeserializedMTGSet> dsets) {

        ArrayList<MTGSet> result = new ArrayList<>(100);

        for(DeserializedMTGSet dset : dsets) {

            result.add(setsTransporter(dset));
        }
        return result;
    }


    public static MTGSet setsTransporter(DeserializedMTGSet dset)
    {

        MTGSet set = new MTGSet();
        ArrayList<MTGCard> cards = new ArrayList<>();
        set.setName(dset.getName());
        set.setBlock(dset.getBlock());
        set.setBorder(dset.getBorder());
        set.setCode(dset.getCode());
        set.setGathererCode(dset.getGathererCode());
        set.setType(dset.getType());
        set.setReleaseDate(dset.getReleaseDate());

        for(DeserializedMTGCard dcard : dset.getCards()){

            MTGCard card = new MTGCard();
            cards.add(cardSetter(card, dcard));

        }
        set.setCards(cards);
        return set;
    }

    private static MTGCard cardSetter(MTGCard card, DeserializedMTGCard dcard)
    {
        card.setLayout(dcard.getLayout());
        card.setName(dcard.getName());

        if(dcard.getNames() != null){
            card.setNames(dcard.getNames().toString());
        }

        card.setManaCost(dcard.getManaCost());
        card.setCmc(dcard.getCmc());

        card.setColors(dcard.getWork_colors());

        card.setType(dcard.getType());
        if(dcard.getSupertypes() != null){
            card.setSupertypes(dcard.getSupertypes().toString());
        }


        if(dcard.getTypes() != null) {
            card.setTypes(dcard.getTypes().toString());
        }
        if(dcard.getSubtypes() != null){
            card.setSubtypes(dcard.getSubtypes().toString());
        }

        card.setRarity(dcard.getRarity());
        card.setText(dcard.getText());
        card.setFlavor(dcard.getFlavor());
        /*Creare metodo per aggiungere artiti*/
        card.setArtist(dcard.getWork_artist());

        card.setNumber(dcard.getNumber());
        card.setPower(dcard.getPower());
        card.setToughness(dcard.getToughness());
        card.setLoyalty(dcard.getLoyalty());
        card.setMultiverseid(dcard.getMultiverseid());

        if(dcard.getVariations() != null){
            card.setVariations(dcard.getVariations().toString());
        }

        card.setImageName(dcard.getImageName());
        card.setBorder(dcard.getBorder());
        card.setWatermark(dcard.getWatermark());
        card.setTimeshifted(dcard.isTimeshifted());
        card.setHand(dcard.getHand());
        card.setLife(dcard.getLife());
        card.setReleaseDate(dcard.getReleaseDate());
        card.setReserved(dcard.isReserved());

        card.setRulings(dcard.getWork_rulings());

        card.setForeignNames(dcard.getWork_foreignNames());

        card.setPrintings(dcard.getPrintings().toString());
        card.setOriginalText(dcard.getOriginalText());
        card.setOriginalType(dcard.getOriginalType());
        card.setSource(dcard.getSource());
        card.setLegalities(dcard.getWork_legalities());
        card.setSetCode(dcard.getSetCode());
        card.setSetName(dcard.getSetName());


        return card;



    }


    //tested
    public static void artistConverter(ArrayList<DeserializedMTGSet> sets)
    {

        Map<String,MTGArtist> artistsetmap = new HashMap<>();

        for(DeserializedMTGSet set : sets) {

            ArrayList<DeserializedMTGCard> cards = set.getCards();

            for(DeserializedMTGCard card : cards) {
                if(card.getArtist()==null) continue;

                if (!artistsetmap.containsKey(card.getArtist())){

                    artistsetmap.put(card.getArtist(),new MTGArtist(card.getArtist()));
                    //System.out.println(card.getName()+ " aggiunto " + card.getArtist()+ " alla hashmap artisti "+ card.getSetCode());
                    //System.out.println("nell'if");
                }

                card.setWork_artist(artistsetmap.get(card.getArtist()));
                //System.out.println(card.getName() + " è stata settata con l'oggetto artist " + artistsetmap.get(card.getArtist())+ "del set " + card.getSetCode());
            }
        }

        System.out.println(artistsetmap.size() + " artisti aggiunti alla MAP");



    }

    //tested
    public static void rulingstoString(ArrayList<DeserializedMTGSet> sets)
    {

        String result  = "";

        for (DeserializedMTGSet set : sets) {

            ArrayList<DeserializedMTGCard> cards = set.getCards();

            for(DeserializedMTGCard card : cards) {

                if (card.getRulings() == null) continue;

                ArrayList<DeserializedMTGCardRuling> rulings = card.getRulings();

                for(DeserializedMTGCardRuling rule : rulings) {

                    result = result + rule.toString();
                }

                card.setWork_rulings(result);
                result= "";

            }
        }
        System.out.println("FINITA CONVERSIONE RULINGS");
    }

    //tested
    public static void foreignNamesConverter(ArrayList<DeserializedMTGSet> sets)
    {


        for (DeserializedMTGSet set : sets) {

            ArrayList<DeserializedMTGCard> cards = set.getCards();

            for(DeserializedMTGCard card : cards) {

                if (card.getForeignNames()== null) continue;

                MTGCardForeignNames work_name = new MTGCardForeignNames();

                ArrayList<DeserializedMTGCardForeignName> names = card.getForeignNames();

                for(DeserializedMTGCardForeignName name : names) {

                    String language = name.getLanguage();
                    switch (language) {
                        case "Chinese Simplified":
                            work_name.setChis(name.getName());
                            break;
                        case "Chinese Traditional":
                            work_name.setChit(name.getName());
                            break;
                        case "French" :
                            work_name.setFra(name.getName());
                            break;
                        case "German" :
                            work_name.setGer(name.getName());
                            break;
                        case "Italian" :
                            work_name.setIta(name.getName());
                            break;
                        case "Japanese" :
                            work_name.setJap(name.getName());
                            break;
                        case "Portuguese (Brazil)" :
                            work_name.setPor(name.getName());
                            break;
                        case "Russian" :
                            work_name.setRus(name.getName());
                            break;
                        case "Spanish" :
                            work_name.setSpa(name.getName());
                            break;
                        case "Korean" :
                            work_name.setKor(name.getName());
                    }
                }
                card.setWork_foreignNames(work_name);
            }
        }
        System.out.println("FINITA CONVERSIONE FOREIGN NAMES");
    }

}
