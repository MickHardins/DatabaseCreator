package com.Deserializer.model;

/**
 * Created by Mick on 19/12/2014.
 */
public class MTGColors
{
    private boolean b;
    private boolean u;
    private boolean w;
    private boolean r;
    private boolean g;

    private String blue;
    private String black;
    private String white;
    private String red;
    private String green;


    /*TODO aggiungere un costruttore che in base a un numero inzializza i campi*/

    public MTGColors(int i) {
        switch (i)
        {
            case 1:
                this.b=true;

        }
    }

}
