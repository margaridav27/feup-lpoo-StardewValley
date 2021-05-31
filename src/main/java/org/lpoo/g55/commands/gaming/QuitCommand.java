package org.lpoo.g55.commands.gaming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;

public final class QuitCommand implements Command {

    private final static String TEXT = "Quit";

    private final Game GAME;

    public QuitCommand(Game game) {
        this.GAME = game;
    }

    @Override
    public String getText() {
        return QuitCommand.TEXT;
    }

    @Override
    public void execute() {
        this.GAME.setState(null);
    }
}