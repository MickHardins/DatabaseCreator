package com.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mick on 19/12/2014.
 */


public class DeserializedMTGCard
{
    @DatabaseField(generatedId = true)
    private Long id;


    private String layout;
    private String name;
    private ArrayList<String> names;
    private String manaCost;
    private float cmc;


    private ArrayList<String> colors;



    private String type;
    private ArrayList<String> supertypes;
    private ArrayList<String> types;
    private ArrayList<String> subtypes;
    private String rarity;
    private String text;
    private String flavor;
    private String artist;
    private String number;
    private String power;
    private String toughness;
    private Integer loyalty;
    private Integer multiverseid;
    private ArrayList<String> variations;
    private String imageName;
    private String border;
    private String watermark;
    private boolean timeshifted;
    private String hand;
    private String life;
    private String releaseDate;
    private String reserved;
    private ArrayList<DeserializedMTGCardRuling> rulings;
    private ArrayList<MTGCardForeignName> foreignNames;
    private ArrayList<String> printings;
    private String originalText;
    private String originalType;
    private HashMap<String, String> legalities;
    private String source;
    @DatabaseField(foreign = true)
    private DeserializedMTGCardLegalities work_legalities;

    // Not part of JSON, will be set later
    private String setCode;
    private String setName;

    public DeserializedMTGCard() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public float getCmc() {
        return cmc;
    }

    public void setCmc(float cmc) {
        this.cmc = cmc;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getSupertypes() {
        return supertypes;
    }

    public void setSupertypes(ArrayList<String> supertypes) {
        this.supertypes = supertypes;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<String> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(ArrayList<String> subtypes) {
        this.subtypes = subtypes;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public Integer getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Integer loyalty) {
        this.loyalty = loyalty;
    }

    public Integer getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(Integer multiverseid) {
        this.multiverseid = multiverseid;
    }

    public ArrayList<String> getVariations() {
        return variations;
    }

    public void setVariations(ArrayList<String> variations) {
        this.variations = variations;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public boolean isTimeshifted() {
        return timeshifted;
    }

    public void setTimeshifted(boolean timeshifted) {
        this.timeshifted = timeshifted;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public ArrayList<MTGCardRulings> getRulings() {
        return rulings;
    }

    public void setRulings(ArrayList<MTGCardRulings> rulings) {
        this.rulings = rulings;
    }

    public ArrayList<MTGCardForeignName> getForeignNames() {
        return foreignNames;
    }

    public void setForeignNames(ArrayList<MTGCardForeignName> foreignNames) {
        this.foreignNames = foreignNames;
    }

    public ArrayList<String> getPrintings() {
        return printings;
    }

    public void setPrintings(ArrayList<String> printings) {
        this.printings = printings;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }

    public HashMap<String, String> getHashlegalities() {
        return legalities;
    }

    public void setHashlegalities(HashMap<String, String> hashlegalities) {
        this.legalities = hashlegalities;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public MTGCardLegalities getWork_legalities() {
        return work_legalities;
    }

    public void setWork_legalities(MTGCardLegalities work_legalities) {
        this.work_legalities = work_legalities;
    }

    public HashMap<String, String> getLegalities() {
        return legalities;
    }

    public void setLegalities(HashMap<String, String> legalities) {
        this.legalities = legalities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MTGCard mtgCard = (MTGCard) o;

        if (Float.compare(mtgCard.cmc, cmc) != 0) return false;
        if (!name.equals(mtgCard.name)) return false;

        return true;
    }


}
