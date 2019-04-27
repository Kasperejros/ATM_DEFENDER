package com.atm.game;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static Position p(int x, int y) {
        return new Position(x, y);
    }
}
