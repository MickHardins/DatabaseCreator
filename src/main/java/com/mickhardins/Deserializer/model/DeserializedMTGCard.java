package com.mickhardins.Deserializer.model;

import com.mickhardins.DatabaseFiller.model.MTGArtist;
import com.mickhardins.DatabaseFiller.model.MTGCardForeignNames;
import com.mickhardins.DatabaseFiller.model.MTGCardLegalities;
import com.mickhardins.DatabaseFiller.model.MTGColors;

import java.util.ArrayList;

/**
 * Created by Mick on 19/12/2014.
 */
public class DeserializedMTGCard {


    private String id;
    private String layout;
    private String name;
    private ArrayList<String> names;
    private String manaCost;
    private float cmc;
    private ArrayList<String> colors;
    private MTGColors work_colors;
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
    private boolean reserved;
    private ArrayList<DeserializedMTGCardRuling> rulings;
    private ArrayList<DeserializedMTGCardForeignName> foreignNames;
    private ArrayList<String> printings;
    private String originalText;
    private String originalType;
    private ArrayList<DeserializedMTGLegalities> legalities;
    private String source;
    private MTGCardLegalities work_legalities;
    private String mciNumber;

    // Not part of JSON, will be set later
    private String setCode;
    private String setName;
    private MTGArtist work_artist;
    private String work_rulings;
    private MTGCardForeignNames work_foreignNames;


    public DeserializedMTGCard() {

    }

    public String getMciNumber() {
        return mciNumber;
    }

    public void setMciNumber(String mciNumber) {
        this.mciNumber = mciNumber;
    }

    public String getWork_rulings() {
        return work_rulings;
    }

    public void setWork_rulings(String work_rulings) {
        this.work_rulings = work_rulings;
    }

    public MTGCardForeignNames getWork_foreignNames() {
        return work_foreignNames;
    }

    public void setWork_foreignNames(MTGCardForeignNames work_foreignNames) {
        this.work_foreignNames = work_foreignNames;
    }

    public MTGArtist getWork_artist() {
        return work_artist;
    }

    public void setWork_artist(MTGArtist work_artist) {
        this.work_artist = work_artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setLegalities(ArrayList<DeserializedMTGLegalities> legalities) {
        this.legalities = legalities;
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

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public ArrayList<DeserializedMTGCardRuling> getRulings() {
        return rulings;
    }

    public void setRulings(ArrayList<DeserializedMTGCardRuling> rulings) {
        this.rulings = rulings;
    }

    public ArrayList<DeserializedMTGCardForeignName> getForeignNames() {
        return foreignNames;
    }

    public void setForeignNames(ArrayList<DeserializedMTGCardForeignName> foreignNames) {
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

    public ArrayList<DeserializedMTGLegalities> getLegalities() {
        return legalities;
    }

    public MTGColors getWork_colors() {
        return work_colors;
    }

    public void setWork_colors(MTGColors work_colors) {
        this.work_colors = work_colors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeserializedMTGCard that = (DeserializedMTGCard) o;

        if (Float.compare(that.cmc, cmc) != 0) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (cmc != +0.0f ? Float.floatToIntBits(cmc) : 0);
        return result;
    }
}
