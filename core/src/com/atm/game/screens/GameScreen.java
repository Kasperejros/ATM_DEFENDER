package com.atm.game.screens;

import com.atm.game.ATM;
import com.atm.game.EnemiesFactory;
import com.atm.game.ObjectsDetector;
import com.atm.game.Game;
import com.atm.game.GameObject;
import com.atm.game.Position;
import com.atm.game.projectile.Projectile;
import com.atm.game.tile.CoordinatesHelper;
import com.atm.game.Cone;
import com.atm.game.defense.Defense;
import com.atm.game.enemy.Enemy;
import com.atm.game.tile.TileManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.List;

public class GameScreen extends AbstractScreen implements InputProcessor {
    private List<GameObject> objects;
    private EnemiesFactory enemiesFactory;
    float lastAddedEnemy = 0;
    private TiledMap tiledMap;
    private TileManager tileManager;

    Position lastTappedTile = null;
    public GameScreen(Game g) {
        super(g);
        objects = new LinkedList<GameObject>();
        enemiesFactory = new EnemiesFactory(objects);
        enemiesFactory.route = route();

        objects.add(enemiesFactory.createEnemy(new Vector2(500f, 500f)));
        objects.add(new Defense(new Vector2(300, 100), new ObjectsDetector(objects, new ObjectsDetector.DetectorPredictate() {
            @Override
            public boolean isDetectable(GameObject o) {
                return o instanceof Enemy;
            }
        })));
        objects.add(new ATM(new Vector2(500f, 300f)));

        tiledMap = new TmxMapLoader().load("Maps/test_map.tmx");
        tileManager = new TileManager(tiledMap);
        tiledMapRenderer = new IsometricTiledMapRenderer(tileManager.map);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        List<GameObject> toAdd = new LinkedList<GameObject>();

        lastAddedEnemy += delta;
        if (lastAddedEnemy > 3f) {
            lastAddedEnemy = 0f;
            objects.add(enemiesFactory.createEnemy(new Vector2(100f, 100f)));
        }
        super.render(delta);
        for (GameObject o : objects) {
            o.update(delta);
            if(o instanceof Defense) {
                Defense d = (Defense) o;
                if(!d.getProjectiles().isEmpty()) {
                    toAdd.addAll(d.getProjectiles());
                    d.resetList();
                }
            }
        }
        for (GameObject object: toAdd) {
            objects.add(object);
        }

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        spriteBatch.begin();
        for (GameObject o : objects) {
            Vector2 position = CoordinatesHelper.twoDToIso(o.getPosition());
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        placeHighlight(screenX, screenY, true);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        placeHighlight(screenX, screenY, false);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private Position placeHighlight(float x, float y,boolean click){
        Vector3 position = new Vector3(x,y,0);
        camera.unproject(position);
        int cellX = (int) Math.floor(CoordinatesHelper.getTileCoordinates(position).x);
        int cellY = (int) Math.floor(CoordinatesHelper.getTileCoordinates(position).y);
        if (click) {
            if (lastTappedTile != null) {
                if (lastTappedTile.x == cellX && lastTappedTile.y == cellY) {
                    float newY = camera.viewportHeight-y;
                    objects.add(new Defense(CoordinatesHelper.isoToTwoD(new Vector2(x, newY)), new ObjectsDetector(objects, new ObjectsDetector.DetectorPredictate() {
                        @Override
                        public boolean isDetectable(GameObject o) {
                            return o instanceof Enemy;
                        }
                    })));
                }
            }
        }
        tileManager.highlightTile(cellX,cellY);
        lastTappedTile = new Position(cellX, cellY);
        return new Position(cellX, cellY);
    }
}
