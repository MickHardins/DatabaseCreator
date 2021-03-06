package com.mickhardins.DatabaseFiller.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mick on 19/12/2014.
 *
 * field marked as transient will not be serialized by Gson
 */

@DatabaseTable(tableName = "Legalities")
public class MTGCardLegalities
{
    @DatabaseField(generatedId = true)
    transient private Long id; //transient is for Gson field exclusion


    @SerializedName("a")
    private String standard;


    @SerializedName("b")
    private String modern;


    @SerializedName("c")
    private String block;

    @DatabaseField
    @SerializedName("d")
    private String legacy;

    @DatabaseField
    @SerializedName("e")
    private String vintage;

    @DatabaseField
    @SerializedName("f")
    transient private String freeform;

    @DatabaseField
    @SerializedName("g")
    transient private String prismatic;

    @DatabaseField
    @SerializedName("h")
    transient private String tribal_wars_legacy;

    @DatabaseField
    @SerializedName("i")
    transient private String tribal_wars_standard;

    @DatabaseField
    @SerializedName("j")
    transient private String singleton100;

    @DatabaseField
    @SerializedName("k")
    private String commander;

    @DatabaseField()
    @SerializedName("l")
    transient private String pauper;

    public MTGCardLegalities()
    {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPauper() {
        return pauper;
    }

    public void setPauper(String pauper) {
        this.pauper = pauper;
    }

    public String getCommander() {
        return commander;
    }

    public void setCommander(String commander) {
        this.commander = commander;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getModern() {
        return modern;
    }

    public void setModern(String modern) {
        this.modern = modern;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLegacy() {
        return legacy;
    }

    public void setLegacy(String legacy) {
        this.legacy = legacy;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public String getFreeform() {
        return freeform;
    }

    public void setFreeform(String freeform) {
        this.freeform = freeform;
    }

    public String getPrismatic() {
        return prismatic;
    }

    public void setPrismatic(String prismatic) {
        this.prismatic = prismatic;
    }

    public String getTribal_wars_legacy() {
        return tribal_wars_legacy;
    }

    public void setTribal_wars_legacy(String tribal_wars_legacy) {
        this.tribal_wars_legacy = tribal_wars_legacy;
    }

    public String getTribal_wars_standard() {
        return tribal_wars_standard;
    }

    public void setTribal_wars_standard(String tribal_wars_standard) {
        this.tribal_wars_standard = tribal_wars_standard;
    }

    public String getSingleton100() {
        return singleton100;
    }

    public void setSingleton100(String singleton100) {
        this.singleton100 = singleton100;
    }
}
