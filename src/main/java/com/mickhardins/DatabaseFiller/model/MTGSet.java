package com.mickhardins.DatabaseFiller.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mick on 21/12/2014.
 */
public class MTGSet {

    @SerializedName("a")
    private String name;

    @SerializedName("b")
    private String code;

    @SerializedName("c")
    private String releaseDate;

    @SerializedName("d")
    private String border;

    @SerializedName("e")
    private String type;

    @SerializedName("f")
    private String block;

    @SerializedName("g")
    private String gathererCode;

    @SerializedName("h")
    private String  magicCardsInfoCode;

    @SerializedName("i")
    private String mkmName;

    @SerializedName("j")
    private int mkmId;

    private Collection<MTGCard> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getGathererCode() {
        return gathererCode;
    }

    public void setGathererCode(String gathererCode) {
        this.gathererCode = gathererCode;
    }

    public Collection<MTGCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<MTGCard> cards) {
        this.cards = cards;
    }

    public String getMagicCardsInfoCode() {
        return magicCardsInfoCode;
    }

    public void setMagicCardsInfoCode(String magicCardsInfoCode) {
        this.magicCardsInfoCode = magicCardsInfoCode;
    }

    public String getMkmName() {
        return mkmName;
    }

    public void setMkmName(String mkmName) {
        this.mkmName = mkmName;
    }

    public int getMkmId() {
        return mkmId;
    }

    public void setMkmId(int mkmId) {
        this.mkmId = mkmId;
    }
}
