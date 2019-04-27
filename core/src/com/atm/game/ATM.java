package com.atm.game;

import com.atm.game.interactions.Interaction;
import com.atm.game.interactions.Withdraw;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ATM extends GameObject implements Interactable {
    private int money = 100;
    public ATM(Vector2 position) {
        this.position = position;
        this.image = TextureCache.getInstance().getTexture("Characters/ATM.png");
    }

    @Override
    public void interact(GameObject interactor, Interaction interaction) {
        if (interaction instanceof Withdraw) {
            int withdrawal = ((Withdraw) interaction).amout;
            money -= withdrawal;
            Gdx.app.log("AA", String.format("WYPŁACIŁ %s, zostao %s", withdrawal, money));
            return;
        }
        Gdx.app.log("INTERACT", "Not a handlable iteracion");

    }
}
