package com.mickhardins.Deserializer.model;

/**
 * Created by mick on 19/05/17.
 * Oggetto da serializzare per correggere i DeserializedMTGset con le info mancanti
 */
public class MTGSetMapped {

    public static final String VALUE_NOT_AVAILABLE = "NOT_AVAILABLE";


    private String name;
    private String code;
    private String gathererCode;
    private String magicCardsInfoCode;
    private String mkmName;
    private int mkmId;
    private String tcgCode; //code di tcgplayer da usare in futuro per l'integrazione con le api prezzi
    private String id;

    public MTGSetMapped() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getGathererCode() {
        return gathererCode;
    }

    public void setGathererCode(String gathererCode) {
        this.gathererCode = gathererCode;
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

    public String getTcgCode() {
        return tcgCode;
    }

    public void setTcgCode(String tcgCode) {
        this.tcgCode = tcgCode;
    }
}
