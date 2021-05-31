package org.lpoo.g55.controller.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.BackToFarmCommand;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.menus.InventoryMenu;
import org.lpoo.g55.model.menus.Menu;

import java.io.IOException;

public class InventoryMenuController extends MenuController<InventoryMenu> {
    private Farm FARM;

    public InventoryMenuController(Menu menu, Farm FARM) {
        super(menu);
        this.FARM = FARM;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                getModel().getEntries().get(getModel().getSelectedEntry()).execute();
                break;
            case BACKSPACE:
                getModel().getEntries().get(getModel().getSelectedEntry() + 1).execute();
                break;
            case ESC:
                new BackToFarmCommand(game, getModel().getFarm()).execute();
                break;
            case QUIT:
                game.setState(null);
                break;
            default:
                break;
        }
    }
}
