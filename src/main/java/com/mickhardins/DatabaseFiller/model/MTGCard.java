package com.mickhardins.DatabaseFiller.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mick on 21/12/2014.
 */

@DatabaseTable(tableName = "Cards")
public class MTGCard {
    
    private String id;

    @SerializedName("a")
    private String layout;

    @SerializedName("b")
    private String name;

    @SerializedName("c")
    private String names;

    @SerializedName("d")
    private String manaCost;

    
    @SerializedName("e")
    private float cmc;

    @DatabaseField(foreign = true)
    @SerializedName("f")
    private MTGColors colors;

    
    @SerializedName("g")
    private String type;

    
    @SerializedName("h")
    private String supertypes;

    
    @SerializedName("i")
    private String types;

    
    @SerializedName("j")
    private String subtypes;

    
    @SerializedName("k")
    private String rarity;

    
    @SerializedName("l")
    private String text;

    
    @SerializedName("m")
    private String flavor;

    @DatabaseField(foreign = true)
    @SerializedName("n")
    private MTGArtist artist;

    
    @SerializedName("o")
    private String number;

    
    @SerializedName("p")
    private String power;

    
    @SerializedName("q")
    private String toughness;

    
    @SerializedName("r")
    private Integer loyalty;

    
    @SerializedName("s")
    private Integer multiverseid;

    
    @SerializedName("t")
    private String variations;

    
    @SerializedName("u")
    private String imageName;

    
    @SerializedName("v")
    private String border;

    
    @SerializedName("w")
    private String watermark;

    
    @SerializedName("x")
    private boolean timeshifted;

    
    @SerializedName("y")
    private String hand;

    
    @SerializedName("z")
    private String life;

    
    @SerializedName("a1")
    private String releaseDate;

    
    @SerializedName("b1")
    private boolean reserved;

    
    @SerializedName("c1")
    private String rulings;

    @DatabaseField(foreign  =true)
    @SerializedName("d1")
    transient private MTGCardForeignNames foreignNames;

    
    @SerializedName("e1")
    private String printings;

    //
    @Expose
    transient private String originalText;

    //
    @Expose
    transient private String originalType;

    
    @SerializedName("f1")
    private String source;


    
    @SerializedName("h1")
    private String setCode;

    
    @SerializedName("i1")
    private String setName;

    @SerializedName("o2")
    private String mciNumber;

    /**
     * Card legalities values
     * Banned     = 0;
     * Legal      = 1;
     * Restricted = 2;
     * Not legal  = 3;
     */
    private int vintageLeg;
    private int legacyLeg;
    private int commanderLeg;
    private int modernLeg;
    private int standardLeg;


    public MTGCard() {

    }

    public String getMciNumber() {
        return mciNumber;
    }

    public void setMciNumber(String mciNumber) {
        this.mciNumber = mciNumber;
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

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
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

    public MTGColors getColors() {
        return colors;
    }

    public void setColors(MTGColors colors) {
        this.colors = colors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupertypes() {
        return supertypes;
    }

    public void setSupertypes(String supertypes) {
        this.supertypes = supertypes;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(String subtypes) {
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

    public MTGArtist getArtist() {
        return artist;
    }

    public void setArtist(MTGArtist artist) {
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

    public String getVariations() {
        return variations;
    }

    public void setVariations(String variations) {
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

    public String getRulings() {
        return rulings;
    }

    public void setRulings(String rulings) {
        this.rulings = rulings;
    }

    public MTGCardForeignNames getForeignNames() {
        return foreignNames;
    }

    public void setForeignNames(MTGCardForeignNames foreignNames) {
        this.foreignNames = foreignNames;
    }

    public String getPrintings() {
        return printings;
    }

    public void setPrintings(String printings) {
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

    public int getVintageLeg() {
        return vintageLeg;
    }

    public void setVintageLeg(int vintageLeg) {
        this.vintageLeg = vintageLeg;
    }

    public int getLegacyLeg() {
        return legacyLeg;
    }

    public void setLegacyLeg(int legacyLeg) {
        this.legacyLeg = legacyLeg;
    }

    public int getCommanderLeg() {
        return commanderLeg;
    }

    public void setCommanderLeg(int commanderLeg) {
        this.commanderLeg = commanderLeg;
    }

    public int getModernLeg() {
        return modernLeg;
    }

    public void setModernLeg(int modernLeg) {
        this.modernLeg = modernLeg;
    }

    public int getStandardLeg() {
        return standardLeg;
    }

    public void setStandardLeg(int standardLeg) {
        this.standardLeg = standardLeg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


