package com.atm.game;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class ObjectsDetector {
    List<GameObject> objects;
    DetectorPredictate predicate;
    public ObjectsDetector(List<GameObject> objects, DetectorPredictate predictate) {
        this.objects = objects;
        this.predicate = predictate;
    }
    public GameObject detectClosest(Vector2 basePosition, float range) {
        GameObject closest = null;
        range = range*range;
        float closestDistance = Float.MAX_VALUE;
        for (GameObject o : objects) {
            if (!predicate.isDetectable(o)) {
                continue;
            }
            float distance = Vector2.dst2(basePosition.x, basePosition.y, o.position.x, o.position.y);
            if (distance < range) {
                if (closest == null) {
                    closest = o;
                    closestDistance = distance;
                } else {
                    if (distance < closestDistance) {
                        closest = o;
                        closestDistance = distance;
                    }
                }
            }
        }
        return closest;
    }
    public interface DetectorPredictate {
        boolean isDetectable(GameObject o);
    }
}

