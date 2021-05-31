package org.lpoo.g55.controller.game.buildings;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.farming.CareCommand;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.game.elements.AnimalController;
import org.lpoo.g55.controller.game.elements.PlayerController;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Barn;

public class BarnController extends Controller<Barn> {
    private final PlayerController<Barn> PLAYER_CONTROLLER;
    private final AnimalController<Barn> ANIMAL_CONTROLLER;

    public BarnController(Barn model) {
        super(model);
        PLAYER_CONTROLLER = new PlayerController<>(model);
        ANIMAL_CONTROLLER = new AnimalController<>(model, model.getAnimals());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case QUIT:
                game.setState(null);
                break;
            case CHAR_C:
                new CareCommand(game, getModel().getFarm()).execute();
                break;
            default:
                PLAYER_CONTROLLER.step(game, action, time);
                ANIMAL_CONTROLLER.step(game, action, time);
                break;
        }
    }
}
