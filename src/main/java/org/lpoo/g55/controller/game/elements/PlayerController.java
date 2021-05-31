package org.lpoo.g55.controller.game.elements;

import org.lpoo.g55.Game;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.states.PlayerInteraction;

public class PlayerController <T extends PlayerControllerInteraction> extends Controller<T> {
    public PlayerController(T model) {
        super(model);
    }

    private void movePlayerLeft() {
        movePlayer(getModel().getPlayer().getPosition().getLeft());
    }
    private void movePlayerRight() {
        movePlayer(getModel().getPlayer().getPosition().getRight());
    }
    private void movePlayerUp() {
        movePlayer(getModel().getPlayer().getPosition().getUp());
    }
    private void movePlayerDown() {
        movePlayer(getModel().getPlayer().getPosition().getDown());
    }

    private void movePlayer(Position position) {
        if (getModel().canPlayerMove(position))
            getModel().getPlayer().setPosition(position);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.UP) movePlayerUp();
        if (action == GUI.ACTION.RIGHT) movePlayerRight();
        if (action == GUI.ACTION.DOWN) movePlayerDown();
        if (action == GUI.ACTION.LEFT) movePlayerLeft();

        ((PlayerInteraction) game.getState()).playerStep(game);
    }
}
