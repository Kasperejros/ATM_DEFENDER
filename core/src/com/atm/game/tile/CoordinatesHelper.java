package com.atm.game.tile;

import com.atm.game.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CoordinatesHelper {

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

    public static Vector2 getTileCoordinates(Vector3 pt){
        Vector2 tileCoordinates = new Vector2(pt.x,pt.y);;
        tileCoordinates.x /= Game.TILEWIDTH;
        tileCoordinates.y = (tileCoordinates.y - Game.TILEHEIGHT/2) / Game.TILEHEIGHT + tileCoordinates.x;
        tileCoordinates.x -= tileCoordinates.y - tileCoordinates.x;
        tileCoordinates.x = tileCoordinates.x + 11;
        tileCoordinates.y = tileCoordinates.y + 2;
        return tileCoordinates;

    }
}
