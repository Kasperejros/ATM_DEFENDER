package com.atm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureCache {
    private static TextureCache instance = null;
    public static TextureCache getInstance() {
        if (instance == null) {
            instance = new TextureCache();
        }
        return instance;
    }
    private TextureCache() {
        this.cache = new HashMap<String, Texture>();
    }
    private HashMap<String, Texture> cache;

    public Texture getTexture(String name) {
        if (!cache.containsKey(name)) {
            cache.put(name, new Texture(Gdx.files.internal(name)));
        }
        return cache.get(name);
    }
}
