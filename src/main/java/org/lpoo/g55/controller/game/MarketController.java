package org.lpoo.g55.controller.game;

import org.lpoo.g55.Game;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.game.elements.PlayerController;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.market.Market;

import static org.lpoo.g55.gui.GUI.ACTION.QUIT;

public class MarketController extends Controller<Market> {
    private final PlayerController PLAYER_CONTROLLER;

    public MarketController(Market model, PlayerController playerController) {
        super(model);
        this.PLAYER_CONTROLLER = playerController;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        PLAYER_CONTROLLER.step(game, action, time);
        if (action == QUIT)
            game.setState(null);
    }
}
