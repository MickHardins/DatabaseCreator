package com.mickhardins.Deserializer.model;

/**
 * Created by mick on 20/04/17.
 */
public class DeserializedMTGLegalities {


    private String format;
    private String legality;

    public DeserializedMTGLegalities() {

    }

    public DeserializedMTGLegalities(String format, String legality) {
        this.format = format;
        this.legality = legality;

    }


    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLegality() {
        return legality;
    }

    public void setLegality(String legality) {
        this.legality = legality;
    }

}
