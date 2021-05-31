package org.lpoo.g55.commands.gaming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.menus.MainMenu;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.states.FarmState;
import org.lpoo.g55.states.menus.MainMenuState;

import java.io.IOException;

public final class BackToMainMenuCommand implements Command {

    private final static String TEXT = "Back";

    private final Game GAME;
    private final Farm FARM;

    public BackToMainMenuCommand(Game GAME, Farm FARM) {
        this.GAME = GAME;
        this.FARM = FARM;
    }

    @Override
    public String getText() {
        return BackToMainMenuCommand.TEXT;
    }

    @Override
    public void execute() throws IOException {
        Size guiSize = new Size(Size.DEFAULT_WORLD_SIZE.getWidth() + 6, Size.DEFAULT_WORLD_SIZE.getHeight() + 6);
        this.GAME.setState(new MainMenuState(new MainMenu(this.GAME, this.FARM, new Position(guiSize.getWidth() / 2 - 6, guiSize.getHeight() / 2 - 6))));
    }
}
