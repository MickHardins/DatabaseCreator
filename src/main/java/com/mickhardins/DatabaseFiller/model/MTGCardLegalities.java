package com.mickhardins.DatabaseFiller.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mick on 19/12/2014.
 */

@DatabaseTable(tableName = "Legalities")
public class MTGCardLegalities
{
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField()
    private String standard;
    private String modern;
    private String block;
    private String legacy;
    private String vintage;
    private String freeform;
    private String prismatic;
    private String tribal_wars_legacy;
    private String tribal_wars_standard;
    private String singleton100;
    private String commander;

    @DatabaseField()
    private String pauper;

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
