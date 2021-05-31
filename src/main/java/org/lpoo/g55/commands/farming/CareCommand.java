package org.lpoo.g55.commands.farming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.elements.animals.barn.BarnAnimal;
import org.lpoo.g55.model.elements.animals.coop.CoopAnimal;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.states.buildings.BarnState;
import org.lpoo.g55.states.buildings.CoopState;

public final class CareCommand implements Command {

    private final static String TEXT = "Care";

    private final Game GAME;
    private final Farm FARM;

    public CareCommand(Game game, Farm farm) {
        this.GAME = game;
        this.FARM = farm;
    }

    @Override
    public String getText() {
        return CareCommand.TEXT;
    }

    @Override
    public void execute() {
        if (this.GAME.getState() instanceof BarnState) {
            for (BarnAnimal animal : this.FARM.getBarn().getAnimals())
                animal.care();
        }
        if (this.GAME.getState() instanceof CoopState) {
            for (CoopAnimal animal : this.FARM.getCoop().getAnimals())
                animal.care();
        }
    }
}
