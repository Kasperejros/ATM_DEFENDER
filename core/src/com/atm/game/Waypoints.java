package com.atm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Waypoints {
    private List<Vector2> points;
    private int listPointer = 0;
    private float tolerance = 10f;
    public Waypoints(Vector2... points ) {
        if (points.length == 0) {
            Gdx.app.log("WTF", "attempt to create empty waypoints guide, expect crash");
        }
        this.points = new LinkedList<Vector2>();
        this.points.addAll(Arrays.asList(points));
    }
    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }
    public void updatePosition(Vector2 position) {
        Vector2 point = getNextDestination();
        if (Vector2.dst(position.x, position.y, point.x, point.y) < tolerance) {
            if (listPointer+1 < points.size()) {
                listPointer++;
            } else {
                reset();
            }
        }
    }
    public Vector2 getNextDestination() {
        Vector2 dest = this.points.get(listPointer);
        return dest;
    }

    public void reset() {
        this.listPointer = 0;
    }
}
