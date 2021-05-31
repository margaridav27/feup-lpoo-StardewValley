package org.lpoo.g55.states;

import org.lpoo.g55.Game;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.game.FarmController;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.states.buildings.BarnState;
import org.lpoo.g55.states.buildings.CoopState;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.game.FarmViewer;

public class FarmState extends State<Farm> implements PlayerInteraction {
    public FarmState(Farm farm) {
        super(farm);
    }

    @Override
    protected Viewer<Farm> getViewer() {
        return new FarmViewer(getModel());
    }

    @Override
    protected Controller<Farm> getController() {
        return new FarmController(getModel());
    }

    @Override
    public void playerStep(Game game) {
        Position playerPosition = getModel().getPlayer().getPosition();

        Position barnPosition = getModel().getBarn().getEntrancePosition();
        if (playerPosition.equals(barnPosition)) {
            Position playerEntrancePosition = getModel().getBarn().getExitPosition().getUp();
            getModel().getPlayer().setPosition(playerEntrancePosition);

            game.setState(new BarnState(getModel().getBarn()));
        }

        Position coopPosition = getModel().getCoop().getEntrancePosition();
        if (playerPosition.equals(coopPosition)) {
            Position playerEntrancePosition = getModel().getCoop().getExitPosition().getUp();
            getModel().getPlayer().setPosition(playerEntrancePosition);

            game.setState(new CoopState(getModel().getCoop()));
        }

        Position marketPosition = getModel().getMarket().getEntrancePosition();
        if (playerPosition.equals(marketPosition)) {
            Position playerEntrancePosition = getModel().getMarket().getExitPosition().getRight();
            getModel().getPlayer().setPosition(playerEntrancePosition);

            game.setState(new MarketState(getModel().getMarket()));
        }
    }
}
