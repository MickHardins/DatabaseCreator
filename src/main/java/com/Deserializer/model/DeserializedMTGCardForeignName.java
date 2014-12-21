package com.Deserializer.model;

/**
 * Created by Mick on 19/12/2014.
 */
public class DeserializedMTGCardForeignName
{
    private String name;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "name= " + name + '\n' +
                "language= " + language + '\n';
    }
}
