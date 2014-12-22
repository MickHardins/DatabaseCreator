package com.mickhardins.DatabaseFiller.model;

/**
 * Created by Mick on 21/12/2014.
 */
public class MTGArtist
{
    private long id;

    private String name;



    public MTGArtist(String name){
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MTGArtist mtgArtist = (MTGArtist) o;

        if (id != mtgArtist.id) return false;
        if (!name.equals(mtgArtist.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
