package com.atm.game.defense;

import com.atm.game.ObjectsDetector;
import com.atm.game.GameObject;
import com.atm.game.TextureCache;
import com.badlogic.gdx.math.Vector2;

public class Defense extends GameObject {
    protected ObjectsDetector detector;
    public Defense(Vector2 position, ObjectsDetector detector) {
        super();
        this.position = position;
        this.detector = detector;
        range = 300f;
        this.image = TextureCache.getInstance().getTexture("Defenses/defense.png");
    }
    private float range;
    @Override
    public void update(float delta) {
        GameObject closest = this.detector.detectClosest(position, range);
        if (closest != null) {
            this.image = TextureCache.getInstance().getTexture("Defenses/defense_detect.png");
        } else {
            this.image = TextureCache.getInstance().getTexture("Defenses/defense.png");
        }
    }


}
