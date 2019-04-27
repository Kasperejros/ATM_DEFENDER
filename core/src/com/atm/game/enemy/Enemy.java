package com.atm.game.enemy;

import com.atm.game.GameObject;
import com.atm.game.Position;
import com.atm.game.TextureCache;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {
    private float maxSpeed = 1f;
    public Enemy(Vector2 position) {
        this.position = position;
        this.image = TextureCache.getInstance().getTexture("Enemies/Hooman.png");
    }

    @Override
    public void update(float delta) {
        Vector2 move = new Vector2();
        move.x = 100;
        position = position.add(move.scl(delta));
        Gdx.app.log("ASDF", String.format("%s", position.x));
    }

}
