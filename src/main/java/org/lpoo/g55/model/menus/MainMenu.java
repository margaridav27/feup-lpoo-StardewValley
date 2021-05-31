package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.InstructionsCommand;
import org.lpoo.g55.commands.gaming.QuitCommand;
import org.lpoo.g55.commands.gaming.StartCommand;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.model.farm.Farm;

import java.util.Arrays;

public class MainMenu extends Menu {

    private final static String TITLE = "Stardew Valley";

    private final static String BACKGROUND = "#000000";
    private final static String FOREGROUND = "#FFFFFF";

    public MainMenu(Game game, Farm farm, Position topLeftCorner) {
        super(game, farm, topLeftCorner);
        this.size = new Size(12, 7);
        this.entries = Arrays.asList(new StartCommand(game), new InstructionsCommand(game, farm), new QuitCommand(game));
    }

    @Override
    public String getTitle() {
        return MainMenu.TITLE;
    }
    @Override
    public String getBackground() {
        return MainMenu.BACKGROUND;
    }
    @Override
    public String getForeground() {
        return MainMenu.FOREGROUND;
    }
}
