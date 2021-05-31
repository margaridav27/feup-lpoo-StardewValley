package org.lpoo.g55.commands.gaming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.menus.InstructionsMenu;
import org.lpoo.g55.states.menus.InstructionsMenuState;

public final class InstructionsCommand implements Command {

    private final static String TEXT = "Instructions";

    private final Game GAME;
    private final Farm FARM;

    public InstructionsCommand(Game game, Farm farm) {
        this.GAME = game;
        this.FARM = farm;
    }

    @Override
    public String getText() {
        return InstructionsCommand.TEXT;
    }

    @Override
    public void execute() {
        this.GAME.setState(new InstructionsMenuState(new InstructionsMenu(this.GAME, this.FARM, new Position(0, 0))));
    }
}
