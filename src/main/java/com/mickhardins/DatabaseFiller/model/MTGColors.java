package com.mickhardins.DatabaseFiller.model;

import com.j256.ormlite.field.DatabaseField;
import com.mickhardins.Deserializer.model.DeserializedMTGCard;

/**
 * Created by Mick on 19/12/2014.
 */
public class MTGColors
{
    @DatabaseField(id = true)
    private int id;

    private boolean b = false;
    private boolean u = false;
    private boolean w = false;
    private boolean r = false;
    private boolean g = false;

    private String blue;
    private String black;
    private String white;
    private String red;
    private String green;

    public static final int BLACK_FLAG  = 1;
    public static final int BLUE_FLAG   = 2;
    public static final int WHITE_FLAG  = 4;
    public static final int RED_FLAG    = 8;
    public static final int GREEN_FLAG  = 16;


    /*TODO aggiungere un costruttore che in base a un numero inzializza i campi*/

    public static int colorID(DeserializedMTGCard card)
    {
        int id = 0;
        if(card.getColors()== null) return id;
        if(card.getColors().contains("Black"))  id = id | BLACK_FLAG;
        if(card.getColors().contains("Blue"))   id = id | BLUE_FLAG;
        if(card.getColors().contains("White"))  id = id | WHITE_FLAG;
        if(card.getColors().contains("Red"))    id = id | RED_FLAG;
        if(card.getColors().contains("Green"))  id = id | GREEN_FLAG;

        return id;
    }

    public MTGColors(int colorID)
    {
        this.id = colorID;

        if((colorID & BLACK_FLAG) == BLACK_FLAG){
            this.b = true;
            this.black="Black";
        }
        if((colorID & BLUE_FLAG) == BLUE_FLAG){
            this.u = true;
            this.blue = "Blue";
        }
        if((colorID & WHITE_FLAG) == WHITE_FLAG){
            this.w = true;
            this.white = "White";
        }
        if((colorID & RED_FLAG) == RED_FLAG){
            this.r = true;
            this.red="Red";
        }
        if((colorID & GREEN_FLAG) == GREEN_FLAG){
            this.g = true;
            this.green="Green";
        }
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
        this.black="Black";
    }

    public boolean isU() {
        return u;
    }

    public void setU(boolean u) {
        this.u = u;
        this.blue="Blue";
    }

    public boolean isW() {
        return w;
    }

    public void setW(boolean w) {
        this.w = w;
        this.white="White";
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
        this.red = "Red";
    }

    public boolean isG() {
        return g;
    }

    public void setG(boolean g) {
        this.g = g;
        this.green = "Green";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
