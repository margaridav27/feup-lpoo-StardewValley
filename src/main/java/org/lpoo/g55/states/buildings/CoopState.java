package org.lpoo.g55.states.buildings;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.farming.CollectCommand;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.game.buildings.CoopController;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.states.FarmState;
import org.lpoo.g55.states.PlayerInteraction;
import org.lpoo.g55.states.State;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.game.buildings.CoopViewer;

public class CoopState extends State<Coop> implements PlayerInteraction {
    public CoopState(Coop model) {
        super(model);
    }

    @Override
    public Viewer<Coop> getViewer() {
        return new CoopViewer(getModel());
    }

    @Override
    protected Controller<Coop> getController() {
        return new CoopController(getModel());
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
