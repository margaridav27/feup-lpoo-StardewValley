package org.lpoo.g55.states.buildings;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.farming.CollectCommand;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.game.buildings.BarnController;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.states.FarmState;
import org.lpoo.g55.states.PlayerInteraction;
import org.lpoo.g55.states.State;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.game.buildings.BarnViewer;

public class BarnState extends State<Barn> implements PlayerInteraction {
    public BarnState(Barn model) {
        super(model);
    }

    @Override
    public Viewer<Barn> getViewer() {
        return new BarnViewer(getModel());
    }

    @Override
    protected Controller<Barn> getController() {
        return new BarnController(getModel());
    }

    @Override
    public void playerStep(Game game) {
        Position playerPosition = getModel().getPlayer().getPosition();

        // if player passes by an animal, he will automatically collect the products if such animal is ready to produce
        if (getModel().isAnimal(playerPosition)) {
            new CollectCommand(getModel().getFarm(), getModel().getAnimal(playerPosition)).execute();
        }

        if (getModel().getExitPosition().equals(playerPosition)) {
            Position playerExitPosition = getModel().getEntrancePosition().getDown();
            getModel().getPlayer().setPosition(playerExitPosition);

            game.setState(new FarmState(getModel().getFarm()));
        }
    }
}
