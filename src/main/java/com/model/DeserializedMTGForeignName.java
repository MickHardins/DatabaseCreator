package com.model;

/**
 * Created by Mick on 19/12/2014.
 */
public class DeserializedMTGForeignName
{
    private String name;
    private String language;



    @Override
    public String toString() {
        return "name= " + name + '\n' +
                "language= " + language + '\n';
    }
}
