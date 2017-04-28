package com.mickhardins.DatabaseFiller.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Mick on 21/12/2014.
 */
@DatabaseTable(tableName = "Artists")
public class MTGArtist
{

    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public void calculateID() {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();

            MessageDigest m = MessageDigest.getInstance("SHA1");
            m.update(baos.toByteArray());

            id =  new BigInteger(1, m.digest()).toString(16);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
