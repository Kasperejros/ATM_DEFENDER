package com.atm.game;

import com.badlogic.gdx.math.Vector2;

public class Cone extends GameObject {
    public Cone(Vector2 position) {
        super();
        this.position = position;
        image = TextureCache.getInstance().getTexture("cone.png");
    }
}
