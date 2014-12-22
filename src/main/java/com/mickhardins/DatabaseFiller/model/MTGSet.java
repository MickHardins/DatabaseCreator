package com.mickhardins.DatabaseFiller.model;

import java.util.Collection;

/**
 * Created by Mick on 21/12/2014.
 */
public class MTGSet
{
    //@DatabaseField(generatedId = true)
    private Long                    id;

   // @DatabaseField()
    private String					name;

    private String					code;
    private String					releaseDate;
    private String					border;
    private String					type;
    private String					block;
    private String					gathererCode;

   // @ForeignCollectionField(eager = true)
    private Collection<MTGCard> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getGathererCode() {
        return gathererCode;
    }

    public void setGathererCode(String gathererCode) {
        this.gathererCode = gathererCode;
    }

    public Collection<MTGCard> getCards() {
        return cards;
    }

    public void setCards(Collection<MTGCard> cards) {
        this.cards = cards;
    }
}
