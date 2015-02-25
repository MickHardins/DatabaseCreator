package com.mickhardins.DatabaseFiller.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mick on 21/12/2014.
 */
@DatabaseTable(tableName = "Artists")
public class MTGArtist
{
    @DatabaseField(generatedId = true)
    transient private long id;

    @DatabaseField()
    @SerializedName("a")
    private String name;



    //@DatabaseField(foreign = true)
    private MTGCard foreigncard;

    public MTGArtist() {

    }

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
