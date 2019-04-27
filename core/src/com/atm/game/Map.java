package com.atm.game;

import com.badlogic.gdx.math.Vector2;

public class Map {
    private Field[][] fields;
    public Map(int width, int height) {
        fields = new Field[width][height];
    }

    class Field {

    }

    public static Vector2 twoDToIso(Vector2 pt){
        Vector2 tmp = new Vector2(0,0);
        tmp.x = pt.x - pt.y;
        tmp.y = (pt.x + pt.y) / 2;
        return(tmp);
    }

    public static Vector2 isoToTwoD(Vector2 pt){
        Vector2 tempPt = new Vector2(0, 0);
        tempPt.x = (2 * pt.y + pt.x) / 2;
        tempPt.y = (2 * pt.y - pt.x) / 2;
        return(tempPt);
    }
}
