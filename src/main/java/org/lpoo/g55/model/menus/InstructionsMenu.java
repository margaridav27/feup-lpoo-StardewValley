package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.BackToMainMenuCommand;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.model.farm.Farm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstructionsMenu extends Menu {

    private final static String TITLE = "Instructions";

    private final static String BACKGROUND = "#000000";
    private final static String FOREGROUND = "#FFFFFF";

    private final List<String> INSTRUCTIONS = new ArrayList<>();

    public InstructionsMenu(Game game, Farm farm, Position topLeftCorner) {
        super(game, farm, topLeftCorner);

        this.size = new Size(12, 7);

        this.entries = Collections.singletonList(new BackToMainMenuCommand(game, farm));

        this.INSTRUCTIONS.add("Arrows: player movement");
        this.INSTRUCTIONS.add("Q: leave the game");
        this.INSTRUCTIONS.add("I: view the inventory -> Farm");
        this.INSTRUCTIONS.add("Esc: leave the inventory");
        this.INSTRUCTIONS.add("H: harvest crops -> Farm");
        this.INSTRUCTIONS.add("W: water crops -> Farm");
        this.INSTRUCTIONS.add("C: crop seeds -> Farm");
        this.INSTRUCTIONS.add("C: care all of the animals -> Barn and Coop");
        this.INSTRUCTIONS.add("Enter: add to chart -> Shops");
        this.INSTRUCTIONS.add("Backspace: remove from chart -> Shops");
        this.INSTRUCTIONS.add("If the caring factor of an animal is full, they change");
        this.INSTRUCTIONS.add("their color and the products can be collected by");
        this.INSTRUCTIONS.add("putting the player in the same position as them.");
        this.INSTRUCTIONS.add("It's the same for the crops but you need to press the");
        this.INSTRUCTIONS.add("H key. States to be collected: READY and DEAD.");
    }

    public List<String> getInstructions() {
        return INSTRUCTIONS;
    }

    @Override
    public String getTitle() {
        return InstructionsMenu.TITLE;
    }
    @Override
    public String getBackground() {
        return InstructionsMenu.BACKGROUND;
    }
    @Override
    public String getForeground() {
        return InstructionsMenu.FOREGROUND;
    }
}
