package com.mickhardins.DatabaseFiller.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mick on 22/04/17.
 */
public class UpdateObject {
    @SerializedName("a")
    private boolean allchanged;

    @SerializedName("b")
    private int version;

    @SerializedName("c")
    private String[] updatedSets;

    @SerializedName("d")
    private String[] updatedSetsUrls;

    public UpdateObject(){
        this.allchanged=false;

    }

    public UpdateObject(int version){
        this.version = version;
    }

    public String[] getUpdatedSetsUrls() {
        return updatedSetsUrls;
    }

    public void setUpdatedSetsUrls(String[] updatedSetsUrls) {
        this.updatedSetsUrls = updatedSetsUrls;
    }

    public boolean isAllchanged() {
        return allchanged;
    }

    public void setAllchanged(boolean allchanged) {
        this.allchanged = allchanged;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String[] getUpdatedSets() {
        return updatedSets;
    }

    public void setUpdatedSets(String[] updatedSets) {
        this.updatedSets = updatedSets;
    }
}
