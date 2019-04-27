package com.atm.game.enemy;

import com.atm.game.ATM;
import com.atm.game.ObjectsDetector;
import com.atm.game.GameObject;
import com.atm.game.TextureCache;
import com.atm.game.Waypoints;
import com.atm.game.interactions.Withdraw;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {
    private float speed = 100f;
    private ObjectsDetector detector;
    private Waypoints route;

    public Enemy(Vector2 position, Waypoints waypoints, ObjectsDetector atmDetector) {
        this.position = position;
        this.route = waypoints;
        this.image = TextureCache.getInstance().getTexture("Enemies/Hooman.png");
        detector = atmDetector;
    }

    @Override
    public void update(float delta) {
        route.updatePosition(position);
        Vector2 destination = new Vector2(route.getNextDestination());
        Vector2 move = destination.sub(position).nor().scl(delta*speed);
        position = position.add(move);
        GameObject detected = detector.detectClosest(position, 100f);
        if (detected != null) {
            if (detected instanceof ATM) {
                Withdraw w = new Withdraw();
                w.amout = 100;
                ((ATM)detected).interact(this, w);
                alive = false;
            }
        }
    }

}
