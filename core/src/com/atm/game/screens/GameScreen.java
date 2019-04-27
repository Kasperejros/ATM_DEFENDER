package com.atm.game.screens;

import com.atm.game.ATM;
import com.atm.game.EnemiesFactory;
import com.atm.game.ObjectsDetector;
import com.atm.game.Game;
import com.atm.game.GameObject;
import com.atm.game.Map;
import com.atm.game.Cone;
import com.atm.game.Position;
import com.atm.game.TextureCache;
import com.atm.game.defense.Defense;
import com.atm.game.enemy.Enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class GameScreen extends AbstractScreen {
    private List<GameObject> objects;
    private Position cursor;
    private Map map;
    private Texture background;
    private EnemiesFactory enemiesFactory;
    float lastAddedEnemy = 0;
    public GameScreen(Game g) {
        super(g);
        objects = new LinkedList<GameObject>();
        enemiesFactory = new EnemiesFactory(objects);
        enemiesFactory.route = route();
        map = new Map(7, 8);
        background = TextureCache.getInstance().getTexture("Maps/Map_TEST.png");

        objects.add(enemiesFactory.createEnemy(new Vector2(500f, 500f)));
        objects.add(new Defense(new Vector2(300, 100), new ObjectsDetector(objects, new ObjectsDetector.DetectorPredictate() {
            @Override
            public boolean isDetectable(GameObject o) {
                return o instanceof Enemy;
            }
        })));
        objects.add(new ATM(new Vector2(500f, 300f)));

    }

    @Override
    public void render(float delta) {

        lastAddedEnemy += delta;
        if (lastAddedEnemy > 3f) {
            lastAddedEnemy = 0f;
            objects.add(enemiesFactory.createEnemy(new Vector2(100f, 100f)));
        }
        super.render(delta);
        for (GameObject o : objects) {
            o.update(delta);
        }
        spriteBatch.begin();
        spriteBatch.draw(background, 0,0);
        for (GameObject o : objects) {
            Vector2 position = Map.twoDToIso(o.getPosition());
            spriteBatch.draw(o.getImage(), position.x, position.y);
        }
        spriteBatch.end();
        removeDeadObjects();
    }

    private Vector2[] route() {
        Vector2[] waypoints = new Vector2[] {
                new Vector2(10f,20f),
                new Vector2(300f, 300f),
                new Vector2(700f, 10f),
                new Vector2(700f, 300f)
        };
        for (Vector2 w : waypoints) {
            objects.add(new Cone(w));
        }
        return waypoints;
    }

    private void removeDeadObjects() {
        List<GameObject> toRemove = new LinkedList<GameObject>();
        for (GameObject o : objects) {
            if (!o.isAlive()) {
                toRemove.add(o);
            }
        }
        for (GameObject o : toRemove) {
            objects.remove(o);
        }
    }
}
