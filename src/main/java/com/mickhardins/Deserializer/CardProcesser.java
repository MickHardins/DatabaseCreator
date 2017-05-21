package com.mickhardins.Deserializer;

import com.google.gson.Gson;
import com.mickhardins.DatabaseFiller.ApplicationController;
import com.mickhardins.DatabaseFiller.Utils;
import com.mickhardins.DatabaseFiller.model.*;
import com.mickhardins.Deserializer.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mick on 19/12/2014.
 */
public class CardProcesser {

    public CardProcesser() {

    }

    /**
     * Clona i set deserializzati e ritorna un arraylist di MTGsetMapped da trasformare in json per aggiungere info a mano
     * @param dSets
     * @return
     */
    public ArrayList<MTGSetMapped> createMappedSets(ArrayList<DeserializedMTGSet> dSets) {
        ArrayList<MTGSetMapped> mappedSets = new ArrayList<>(dSets.size());
        for(DeserializedMTGSet dset : dSets) {
            MTGSetMapped mappedSet = new MTGSetMapped();
            mappedSet.setCode(dset.getCode() == null ? MTGSetMapped.VALUE_NOT_AVAILABLE : dset.getCode());
            mappedSet.setGathererCode(dset.getGathererCode() == null ? MTGSetMapped.VALUE_NOT_AVAILABLE : dset.getGathererCode());
            mappedSet.setId(dset.getId() == null ? MTGSetMapped.VALUE_NOT_AVAILABLE : dset.getId());
            mappedSet.setMagicCardsInfoCode(dset.getMagicCardsInfoCode() == null ? MTGSetMapped.VALUE_NOT_AVAILABLE : dset.getMagicCardsInfoCode());
            mappedSet.setMkmId(dset.getMkm_id() == 0 ? 0 : dset.getMkm_id()); //todo check null values behaviour
            mappedSet.setMkmName(dset.getMkm_name() == null ? MTGSetMapped.VALUE_NOT_AVAILABLE : dset.getMkm_name());
            mappedSet.setTcgCode(dset.getTcgCode() == null ? MTGSetMapped.VALUE_NOT_AVAILABLE : dset.getTcgCode());
            mappedSet.setName(dset.getName() == null ? MTGSetMapped.VALUE_NOT_AVAILABLE : dset.getName());
            mappedSets.add(mappedSet);
        }
        return mappedSets;
    }

    public HashMap<String, MTGSetMapped> createSetCodeMappedSetHashMap(MTGSetMapped[] mappedSets) {
        HashMap<String,MTGSetMapped> result = new HashMap<>();
        for (MTGSetMapped set : mappedSets) {
            result.put(set.getCode(), set);
        }
        return result;
    }

    /**
     * Corregge una lista di Deserialized mtgsets a partire da un'hashmap di MTGsetMapped
     * L'hash map viene costruita dopo aver deserializzato il file corretto a mano con i dati dei set
     * @param dsets
     * @param mappedHashMap
     */
    public void correctDeserializedMtgSets(ArrayList<DeserializedMTGSet> dsets, HashMap<String, MTGSetMapped> mappedHashMap) {
        for (DeserializedMTGSet dset : dsets) {
            MTGSetMapped mappedSet = mappedHashMap.get(dset.getCode());
            if (dset.getMagicCardsInfoCode() == null && !mappedSet.getMagicCardsInfoCode().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                dset.setMagicCardsInfoCode(mappedSet.getMagicCardsInfoCode());
            }
            else if (dset.getMagicCardsInfoCode() != null && mappedSet.getMagicCardsInfoCode().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                mappedSet.setMagicCardsInfoCode(dset.getMagicCardsInfoCode());
                //mappedHashMap.put(mappedSet.getCode(), mappedSet);
            }
            if (dset.getMkm_id() == 0 && mappedSet.getMkmId() != 0) {
                dset.setMkm_id(mappedSet.getMkmId());
            }
            else if (dset.getMkm_id() != 0 && mappedSet.getMkmId() == 0) {
                mappedSet.setMkmId(dset.getMkm_id());
            }
            if (dset.getMkm_name() == null && !mappedSet.getMkmName().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                dset.setMkm_name(mappedSet.getMkmName());
            }
            else if (dset.getMkm_name() != null && mappedSet.getMkmName().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                mappedSet.setMkmName(dset.getMkm_name());
            }
            if (dset.getTcgCode() == null && !mappedSet.getTcgCode().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                dset.setTcgCode(mappedSet.getTcgCode());
            }
            else if (dset.getTcgCode() != null && mappedSet.getTcgCode().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                mappedSet.setTcgCode(dset.getTcgCode());
            }
            if (dset.getGathererCode() == null && !mappedSet.getGathererCode().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                dset.setGathererCode(mappedSet.getGathererCode());
            }
            else if (dset.getGathererCode() != null && mappedSet.getGathererCode().equals(MTGSetMapped.VALUE_NOT_AVAILABLE)) {
                mappedSet.setGathererCode(dset.getGathererCode());
            }
            mappedHashMap.put(mappedSet.getCode(), mappedSet);

        }

    }




    //tested
    /* TODO dal momento che pauper è un formato nato per l'online valutare se aggiornare o meno

     */
    public  boolean isInTheBanList(DeserializedMTGCard c) {
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
    public  boolean hasBeenPrintedCommon(ArrayList<DeserializedMTGSet> sets, DeserializedMTGCard c, ArrayList<String> printings) {

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
    public  void pauperLegalitiesAdder(ArrayList<DeserializedMTGSet> sets) {

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
    public void legalityArrToObject(DeserializedMTGCard c ) {

        ArrayList<DeserializedMTGLegalities> legalityList = c.getLegalities();
        if(legalityList == (null)){
            return;
        }

        MTGCardLegalities updated_legalities = new MTGCardLegalities();

        //if (map.entrySet().equals(null)){System.out.print(c.getName() + "porcoddio");}


        for( DeserializedMTGLegalities legality : legalityList) {
            /*valuta il nome del formato e inserisci il valore associato nel giusto campo dell'oggetto MTGCardLegalities*/
            String formato = legality.getFormat();
            if(formato.contains("Block")) {

                updated_legalities.setBlock(legality.getLegality());
                continue;

            }
            switch(formato) {
                case "Standard":
                    updated_legalities.setStandard(legality.getLegality());
                    break;
                case "Modern":
                    updated_legalities.setModern(legality.getLegality());
                    break;
                case "Legacy":
                    updated_legalities.setLegacy(legality.getLegality());
                    break;
                case "Vintage":
                    updated_legalities.setVintage(legality.getLegality());
                    break;
                case "Freeform":
                    updated_legalities.setFreeform(legality.getLegality());
                    break;
                case "Prismatic":
                    updated_legalities.setPrismatic(legality.getLegality());
                    break;
                case "Tribal Wars Legacy":
                    updated_legalities.setTribal_wars_legacy(legality.getLegality());
                    break;
                case "Tribal Wars Standard":
                    updated_legalities.setTribal_wars_standard(legality.getLegality());
                    break;
                case "Singleton 100":
                    updated_legalities.setSingleton100(legality.getLegality());
                    break;
                case "Commander":
                    updated_legalities.setCommander(legality.getLegality());
                    break;

            }

        }

        c.setWork_legalities(updated_legalities);

    }

    public MTGCard setCardLegalitiesFields(MTGCard card, DeserializedMTGCard dCard) {
        //todo valutare se inserire il valore 3 perle carte non legali (non bannate non ristrette ma nn legali) oppure un null value
        MTGCardLegalities legalities = dCard.getWork_legalities();
        if (legalities == null) {
            return card;
        }
        String vintageLeg = legalities.getVintage();
        String commanderLeg = legalities.getCommander();
        String modernLeg = legalities.getModern();
        String legacyLeg = legalities.getLegacy();
        String standardLeg = legalities.getStandard();

        //~~~~~~~~~~~ Vintage ~~~~~~~~~~~
        if (vintageLeg != null) {
            if (vintageLeg.equalsIgnoreCase("Legal")) {
                card.setVintageLeg(1);
            }
            else if (vintageLeg.equalsIgnoreCase("Banned")) {
                card.setVintageLeg(0);
            }
            else {
                card.setVintageLeg(2);
            }
        }
        else {
            card.setVintageLeg(3);
        }

        //~~~~~~~~~~~ Commander ~~~~~~~~~~~
        if (commanderLeg !=null) {
            if (commanderLeg.equalsIgnoreCase("Legal")) {
                card.setCommanderLeg(1);
            }
            else if (commanderLeg.equalsIgnoreCase("Banned")) {
                card.setCommanderLeg(0);
            }
            else {
                card.setCommanderLeg(2);
            }
        }
        else {
            card.setCommanderLeg(3);
        }


        //~~~~~~~~~~~ Standard ~~~~~~~~~~~
        if (standardLeg != null) {
            if (standardLeg.equalsIgnoreCase("Legal")) {
                card.setStandardLeg(1);
            }
            else if (standardLeg.equalsIgnoreCase("Banned")) {
                card.setStandardLeg(0);
            }
            else {
                card.setStandardLeg(2);
            }
        }
        else {
            card.setStandardLeg(3);
        }

        //~~~~~~~~~~~ Modern ~~~~~~~~~~~
        if (modernLeg != null) {
            if (modernLeg.equalsIgnoreCase("Legal")) {
                card.setModernLeg(1);
            }
            else if (modernLeg.equalsIgnoreCase("Banned")) {
                card.setModernLeg(0);
            }
            else {
                card.setModernLeg(2);
            }
        }
        else {
            card.setModernLeg(3);
        }



        //~~~~~~~~~~~ Legacy~~~~~~~~~~~
        if (legacyLeg != null) {
            if (legacyLeg.equalsIgnoreCase("Legal")) {
                card.setLegacyLeg(1);
            }
            else if (vintageLeg.equalsIgnoreCase("Banned")) {
                card.setLegacyLeg(0);
            }
            else {
                card.setLegacyLeg(2);
            }
        }
        else {
            card.setLegacyLeg(3);
        }


        return card;
    }

    //tested
    public void colorObjectAdder(ArrayList<DeserializedMTGSet> sets) {
        /*Per ogni carta del set, calcolo i colori,li aggiungo alla mappa, per ogni carta
        se il colore è presente nella hashmap glielo assegno  */

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

    }

    public ArrayList<MTGSet> transportAllSets(ArrayList<DeserializedMTGSet> dsets) {

        ArrayList<MTGSet> result = new ArrayList<>(200);

        for(DeserializedMTGSet dset : dsets) {

            result.add(setsTransporter(dset));
        }
        return result;
    }


    public void addSetCodeAndNameToCards(ArrayList<DeserializedMTGSet> dSets) {

        for (DeserializedMTGSet dset : dSets) {

            ArrayList<DeserializedMTGCard> cards = dset.getCards();

            for (DeserializedMTGCard card : cards) {

                card.setSetCode(dset.getCode());
                card.setSetName(dset.getName());
                card.setMagicCardsInfoCode(dset.getMagicCardsInfoCode());
            }
        }
    }

    private MTGSet setsTransporter(DeserializedMTGSet dset) {

        MTGSet set = new MTGSet();
        ArrayList<MTGCard> cards = new ArrayList<>();
        set.setName(dset.getName());
        set.setBlock(dset.getBlock());
        set.setBorder(dset.getBorder());
        set.setCode(dset.getCode());
        set.setGathererCode(dset.getGathererCode());
        set.setType(dset.getType());
        set.setReleaseDate(dset.getReleaseDate());
        set.setMkmId(dset.getMkm_id());
        set.setMkmName(dset.getMkm_name());
        set.setMagicCardsInfoCode(dset.getMagicCardsInfoCode());
        set.setTcgCode(dset.getTcgCode());

        for (DeserializedMTGCard dcard : dset.getCards()) {

            legalityArrToObject(dcard);
            MTGCard card = new MTGCard();
            if (dcard.getBorder() == null) {
                String output = set.getBorder().substring(0, 1).toUpperCase() + set.getBorder().substring(1);
                dcard.setBorder(output);
            }
            card = setCardLegalitiesFields(card, dcard);
            cards.add(cardSetter(card, dcard));

        }
        set.setCards(cards);
        return set;
    }

    /**
     * Questa funzione aggiunge i numeri di magiccards.info a partire dai file .txt nella cartella
     * input_files/cnumber_txt_files
     * usiamo l'hashmap generata a partire dalla lettura dei file: occorre tenere conto delle carte con lo stesso
     * nome ma numero diverso
     *
     * @param dSets
     * @throws Exception
     */
    public void addMciCardNumbers(ArrayList<DeserializedMTGSet> dSets) throws Exception {
        for (DeserializedMTGSet dset : dSets) {
            String setCode = dset.getCode();
            HashMap<String, String> hashMap = Utils.generateCardNumbersHashmap(setCode);
            if (hashMap == null) {
                continue;
            }
            else {
                ArrayList<DeserializedMTGCard> dCards = dset.getCards();
                HashMap<String, String> processedCards = new HashMap<>(dset.getCards().size());

                for (DeserializedMTGCard dCard : dCards) {
                    if (dCard.getMciNumber() == null) {

                        String cardName = dCard.getName();

                        if (!processedCards.containsKey(cardName)) {
                            String mciNumber = hashMap.get(cardName);
                            processedCards.put(cardName, mciNumber);
                            dCard.setMciNumber(mciNumber);
                        }
                        else if (processedCards.containsKey(cardName)) {
                            String mciNumber = Integer.toString(Integer.valueOf(processedCards.get(cardName)) + 1);
                            processedCards.put(cardName, mciNumber); //aggiorno il numero
                            dCard.setMciNumber(mciNumber);
                        }
                    }
                }
                dset.getCode();
            }
        }
    }


    private MTGCard cardSetter(MTGCard card, DeserializedMTGCard dcard) {
        card.setId(dcard.getId());
        card.setLayout(dcard.getLayout());
        card.setName(dcard.getName());

        if (dcard.getNames() != null) {
            ArrayList names = dcard.getNames();
            String JSONnames = convertArraylistToJSONString(names);

            card.setNames(JSONnames);
        }

        card.setManaCost(dcard.getManaCost());
        card.setCmc(dcard.getCmc());

        card.setColors(dcard.getWork_colors());

        card.setType(dcard.getType());
        if (dcard.getSupertypes() != null) {
            card.setSupertypes(dcard.getSupertypes().toString());
        }


        if (dcard.getTypes() != null) {
            card.setTypes(dcard.getTypes().toString());
        }
        if (dcard.getSubtypes() != null) {
            card.setSubtypes(dcard.getSubtypes().toString());
        }

        card.setRarity(dcard.getRarity());
        card.setText(dcard.getText());
        card.setFlavor(dcard.getFlavor());
        /*Creare metodo per aggiungere artisti*/
        card.setArtist(dcard.getWork_artist());

        card.setNumber(dcard.getNumber());
        card.setMciNumber(dcard.getMciNumber());
        card.setPower(dcard.getPower());
        card.setToughness(dcard.getToughness());
        card.setLoyalty(dcard.getLoyalty());
        card.setMultiverseid(dcard.getMultiverseid());

        if (dcard.getVariations() != null) {
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

        ArrayList<String> printings = dcard.getPrintings();
        String jsonPrintings = convertArraylistToJSONString(printings);

        card.setPrintings(jsonPrintings);

        card.setOriginalText(dcard.getOriginalText());
        card.setOriginalType(dcard.getOriginalType());
        card.setSource(dcard.getSource());

        card.setSetCode(dcard.getSetCode());
        card.setSetName(dcard.getSetName());
        card.setMagicCardsInfoCode(dcard.getMagicCardsInfoCode());


        return card;
    }

    private String convertArraylistToJSONString(ArrayList<String> input){
        String[] outArr = new String[input.size()];
        outArr = input.toArray(outArr);
        Gson gson = new Gson();
        String jsonArray = gson.toJson(outArr);
        return jsonArray;
    }

    //tested
    public void artistConverter(ArrayList<DeserializedMTGSet> sets) {

        Map<String,MTGArtist> artistsetmap = new HashMap<>();

        for (DeserializedMTGSet set : sets) {

            ArrayList<DeserializedMTGCard> cards = set.getCards();

            for (DeserializedMTGCard card : cards) {

                if (card.getArtist()==null) continue;

                if (!artistsetmap.containsKey(card.getArtist())) {

                    MTGArtist artist = new MTGArtist(card.getArtist());
                    artist.calculateID();

                    artistsetmap.put(card.getArtist(), artist);
                    //System.out.println(card.getName()+ " aggiunto " + card.getArtist()+ " alla hashmap artisti "+ card.getSetCode());
                    //System.out.println("nell'if");
                }

                card.setWork_artist(artistsetmap.get(card.getArtist()));
            }
        }

        System.out.println("LOG:\t" + artistsetmap.size() + " artisti salvati nell'HashMap");
    }

    //tested
    public void rulingstoString(ArrayList<DeserializedMTGSet> sets) {

        String result  = "";

        for (DeserializedMTGSet set : sets) {

            ArrayList<DeserializedMTGCard> cards = set.getCards();

            for (DeserializedMTGCard card : cards) {

                if (card.getRulings() == null) continue;

                ArrayList<DeserializedMTGCardRuling> rulings = card.getRulings();

                for (DeserializedMTGCardRuling rule : rulings) {

                    result = result + rule.toString();
                }

                card.setWork_rulings(result);
                result= "";

            }
        }
        System.out.println("LOG:\tCompletata conversione rulings");
    }

    //tested
    public void foreignNamesConverter(ArrayList<DeserializedMTGSet> sets) {


        for (DeserializedMTGSet set : sets) {

            ArrayList<DeserializedMTGCard> cards = set.getCards();

            for (DeserializedMTGCard card : cards) {

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
        System.out.println("LOG:\tCompletata conversione foreign names");
    }

}
