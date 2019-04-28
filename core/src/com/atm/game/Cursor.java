package com.atm.game;

import com.badlogic.gdx.math.Vector2;

public class Cursor extends GameObject {
    public Cursor() {
        this.image = TextureCache.getInstance().getTexture("cursor.png");
        position = new Vector2(-100f, -100f);
    }
    public void setPosition(Vector2 pos) {
        this.position = pos;
    }
}
