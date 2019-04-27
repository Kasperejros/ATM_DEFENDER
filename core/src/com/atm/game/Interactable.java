package com.atm.game;

import com.atm.game.interactions.Interaction;

public interface Interactable {
    void interact(GameObject interactor, Interaction interaction);
}