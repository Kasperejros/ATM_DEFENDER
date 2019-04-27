package com.atm.game;

import com.atm.game.enemy.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class EnemiesFactory {
    ObjectsDetector atmDetector;
    ObjectsDetector.DetectorPredictate predictate;
    public Vector2[] route;

    public EnemiesFactory(List<GameObject> objects) {
        predictate = new ObjectsDetector.DetectorPredictate()
        {
            @Override
            public boolean isDetectable(GameObject o) {
                return o instanceof ATM;
            }
        };
        atmDetector = new ObjectsDetector(objects, predictate);
    }

    public Enemy createEnemy(Vector2 position) {
        Enemy e = new Enemy(position, new Waypoints(route), atmDetector);

        return e;
    }
}
