package org.lpoo.g55.commands.gaming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.FarmBuilder;
import org.lpoo.g55.states.FarmState;

public final class StartCommand implements Command {

    private final static String TEXT = "Start";

    private final Game GAME;

    public StartCommand(Game game) {
        this.GAME = game;
    }

    @Override
    public String getText() {
        return StartCommand.TEXT;
    }

    @Override
    public void execute() {
        this.GAME.setState(new FarmState(new FarmBuilder().buildFarm()));
    }
}
