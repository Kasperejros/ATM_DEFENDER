package com.atm.game.enemy;

import com.atm.game.GameObject;
import com.atm.game.Position;
import com.atm.game.TextureCache;
import com.atm.game.Waypoints;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {
    private float speed = 100f;

    private Waypoints route;
    public Enemy(Vector2 position, Waypoints waypoints) {
        this.position = position;
        this.route = waypoints;
        this.image = TextureCache.getInstance().getTexture("Enemies/Hooman.png");
    }

    @Override
    public void update(float delta) {
        route.updatePosition(position);
        Vector2 destination = new Vector2(route.getNextDestination());
        Vector2 move = destination.sub(position).nor().scl(delta*speed);
        position = position.add(move);
    }

}
