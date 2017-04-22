package com.mickhardins.DatabaseFiller.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mick on 21/12/2014.
 */
@DatabaseTable(tableName = "ForeignNames")
public class MTGCardForeignNames
{

    @DatabaseField(generatedId = true)
     transient private Long id;

    private String eng;

    @DatabaseField()
    @SerializedName("a")
    private String rus;

    @DatabaseField()
    @SerializedName("b")
    private String spa;

    @DatabaseField
    @SerializedName("c")
    private String chis;


    @DatabaseField
    @SerializedName("d")
    private String chit;


    @DatabaseField
    @SerializedName("e")
    private String ita;


    @DatabaseField
    @SerializedName("f")
    private String fra;

    @DatabaseField
    @SerializedName("g")
    private String jap;

    @DatabaseField
    @SerializedName("h")
    private String kor;

    @DatabaseField
    @SerializedName("i")
    private String por;

    @DatabaseField
    @SerializedName("j")
    private String ger;

    @DatabaseField(foreign = true)
    private MTGCard foreigncard; //required by ormlite

    public MTGCardForeignNames()
    {

    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public String getSpa() {
        return spa;
    }

    public void setSpa(String spa) {
        this.spa = spa;
    }

    public String getChis() {
        return chis;
    }

    public void setChis(String chis) {
        this.chis = chis;
    }

    public String getChit() {
        return chit;
    }

    public void setChit(String chit) {
        this.chit = chit;
    }

    public String getIta() {
        return ita;
    }

    public void setIta(String ita) {
        this.ita = ita;
    }

    public String getFra() {
        return fra;
    }

    public void setFra(String fra) {
        this.fra = fra;
    }

    public String getJap() {
        return jap;
    }

    public void setJap(String jap) {
        this.jap = jap;
    }

    public String getKor() {
        return kor;
    }

    public void setKor(String kor) {
        this.kor = kor;
    }

    public String getPor() {
        return por;
    }

    public void setPor(String por) {
        this.por = por;
    }

    public String getGer() {
        return ger;
    }

    public void setGer(String ger) {
        this.ger = ger;
    }
}
