package com.mickhardins.DatabaseFiller.model;

/**
 * Created by mick on 22/04/17.
 */
public class SetCodesURLObject {
    String[] setURLs;
    int totalSets;

    public SetCodesURLObject(String[] arr,int totalSets){
        setURLs = arr;
        this.totalSets=totalSets;

    }
}
