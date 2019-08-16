package com.atm.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject {
    protected Texture image;
    protected Vector2 position;
    protected Vector2 offset = new Vector2(-32, 0);
    protected boolean alive = true;
    public boolean isAlive() {
        return alive;
    }
    public void update(float deltaTime) {

    }
    public Vector2 getPosition() {
        return new Vector2(this.position);
    }
    public Vector2 getOffset() {
        return new Vector2(this.offset);
    }
    public Texture getImage() {
        return this.image;
    }
}
