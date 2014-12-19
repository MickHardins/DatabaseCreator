package com.Deserializer.model;

/**
 * Created by Mick on 19/12/2014.
 */
public class DeserializedMTGCardRuling
{
    private String date;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text)             {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "date: "+ this.getDate() + "\n text:" +this.getText();
    }
}

