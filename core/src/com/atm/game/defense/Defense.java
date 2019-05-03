package com.atm.game.defense;

import com.atm.game.ObjectsDetector;
import com.atm.game.GameObject;
import com.atm.game.TextureCache;
import com.atm.game.enemy.Enemy;
import com.badlogic.gdx.math.Vector2;

public class Defense extends GameObject {
    protected float damage = 10f;
    protected ObjectsDetector detector;

    protected float cooldownCounter = 0f;
    protected float cooldownTime = 20f;
    public Defense(Vector2 position, ObjectsDetector detector) {
        super();
        this.position = position;
        this.detector = detector;
        range = 100f;
        this.image = TextureCache.getInstance().getTexture("Defenses/defense.png");
    }
    private float range;
    @Override
    public void update(float delta) {
        if (cooldownCounter <= 0) {
            GameObject closest = this.detector.detectClosest(position, range);

            if (closest != null) {
                this.image = TextureCache.getInstance().getTexture("Defenses/defense_detect.png");
                if (closest instanceof Enemy) {
                    ((Enemy) closest).hit(damage);
                    cooldownCounter = cooldownTime;
                }

            } else {
                this.image = TextureCache.getInstance().getTexture("Defenses/defense.png");
            }
        } else {
            this.image = TextureCache.getInstance().getTexture("Defenses/defense.png");
        }
        if (cooldownCounter > 0) {
            cooldownCounter-= delta;
        }
    }


}
