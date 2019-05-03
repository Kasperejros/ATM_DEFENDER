package com.atm.game.projectile;

import com.atm.game.GameObject;
import com.atm.game.ObjectsDetector;
import com.atm.game.TextureCache;
import com.atm.game.defense.Defense;
import com.atm.game.enemy.Enemy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends GameObject {
    private Defense defense;
    private GameObject targetedObject;
    protected float damage = 10f;
    protected ObjectsDetector detector;

    public Projectile(Defense defense, GameObject targetedObject, ObjectsDetector detector) {
        super();
        this.defense = defense;
        this.targetedObject = targetedObject;
        this.position = new Vector2(defense.getPosition().x, defense.getPosition().y);
        this.detector = detector;

        this.image = TextureCache.getInstance().getTexture("Projectiles/bullet.png");
    }
    private float range;
    @Override
    public void update(float delta) {
        Vector2 movement = new Vector2(targetedObject.getPosition().sub(this.position));
        if(movement.x > 0) {
            this.position.x--;
        } else {
            this.position.x++;
        }

        if(movement.y > 0) {
            this.position.y--;
        } else {
            this.position.y++;
        }
    }


}
