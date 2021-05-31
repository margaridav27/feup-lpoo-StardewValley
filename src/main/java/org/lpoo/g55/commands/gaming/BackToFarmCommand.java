package org.lpoo.g55.commands.gaming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.states.FarmState;

public final class BackToFarmCommand implements Command {

    private final static String TEXT = "Back";

    private final Game GAME;
    private final Farm FARM;

    public BackToFarmCommand(Game game, Farm farm) {
        this.GAME = game;
        this.FARM = farm;
    }

    @Override
    public String getText() {
        return BackToFarmCommand.TEXT;
    }

    @Override
    public void execute() {
        this.GAME.setState(new FarmState(this.FARM));
    }
}
