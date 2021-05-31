package org.lpoo.g55.controller.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.menus.AnimalShopMenu;
import org.lpoo.g55.model.menus.CropShopMenu;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.model.utils.Position;

import java.io.IOException;
import java.util.Map;

public class CropShopMenuController extends MenuController<CropShopMenu> {
    public CropShopMenuController(Menu menu) {
        super(menu);
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
            case QUIT:
                game.setState(null);
                break;
        }
    }
}
