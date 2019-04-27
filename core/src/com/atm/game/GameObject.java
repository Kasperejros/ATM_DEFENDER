package com.atm.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject extends Actor {
    protected Texture image;
    protected Vector2 position;

    public void update(float deltaTime) {

    }
    public Vector2 getPosition() {
        return this.position;
    }
    public Texture getImage() {
        return this.image;
    }
}
