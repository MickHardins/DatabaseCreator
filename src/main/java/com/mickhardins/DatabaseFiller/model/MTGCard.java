package com.mickhardins.DatabaseFiller.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mick on 21/12/2014.
 */

@DatabaseTable(tableName = "Cards")
public class MTGCard
{

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField()
    private String layout;

    @DatabaseField()
    private String name;

    @DatabaseField()
    private String names;

    @DatabaseField()
    private String manaCost;

    @DatabaseField()
    private float cmc;

    @DatabaseField(foreign = true)
    private MTGColors colors;


    @DatabaseField()
    private String type;

    @DatabaseField()
    private String supertypes;

    @DatabaseField()
    private String types;

    @DatabaseField()
    private String subtypes;

    @DatabaseField()
    private String rarity;

    @DatabaseField()
    private String text;

    @DatabaseField()
    private String flavor;

    @DatabaseField(foreign = true)
    private MTGArtist artist;

    @DatabaseField()
    private String number;

    @DatabaseField()
    private String power;

    @DatabaseField()
    private String toughness;

    @DatabaseField()
    private Integer loyalty;

    @DatabaseField()
    private Integer multiverseid;

    @DatabaseField()
    private String variations;

    @DatabaseField()
    private String imageName;

    @DatabaseField()
    private String border;

    @DatabaseField()
    private String watermark;

    @DatabaseField()
    private boolean timeshifted;

    @DatabaseField()
    private String hand;

    @DatabaseField()
    private String life;

    @DatabaseField()
    private String releaseDate;

    @DatabaseField()
    private boolean reserved;

    @DatabaseField()
    private String rulings;

    @DatabaseField(foreign  =true)
    private MTGCardForeignNames foreignNames;

    @DatabaseField()
    private String printings;

    //@DatabaseField()
    private String originalText;

    //@DatabaseField()
    private String originalType;

    @DatabaseField()
    private String source;

    @DatabaseField(foreign = true)
    private MTGCardLegalities legalities;

    @DatabaseField()
    private String setCode;

    @DatabaseField()
    private String setName;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private MTGSet MTGset;  //required by ormlite

    public MTGCard()
    {

    }

    public MTGSet getMTGset() {
        return MTGset;
    }

    public void setMTGset(MTGSet MTGset) {
        this.MTGset = MTGset;
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

    public MTGCardLegalities getLegalities() {
        return legalities;
    }

    public void setLegalities(MTGCardLegalities legalities) {
        this.legalities = legalities;
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

}


