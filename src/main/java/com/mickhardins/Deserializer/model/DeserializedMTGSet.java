package com.mickhardins.Deserializer.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by Mick on 19/12/2014.
 */
public class DeserializedMTGSet
{
    //@DatabaseField(generatedId = true)
   transient private String id;

    //@DatabaseField()

    private String name;


    private String code;


    private String releaseDate;


    private String border;

    private String type;


    private String block;

    private String gathererCode;

   // @ForeignCollectionField(eager = true)
    private ArrayList<DeserializedMTGCard> cards;

    public DeserializedMTGSet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getBorder()
    {
        return border;
    }

    public void setBorder(String border)
    {
        this.border = border;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getBlock()
    {
        return block;
    }

    public void setBlock(String block)
    {
        this.block = block;
    }

    public ArrayList<DeserializedMTGCard> getCards()
    {
        return cards;
    }

    public void setCards(ArrayList<DeserializedMTGCard> cards)
    {
        this.cards = cards;
    }

    public String getGathererCode()
    {
        return gathererCode;
    }

    public void setGathererCode(String gathererCode)
    {
        this.gathererCode = gathererCode;
    }
}
