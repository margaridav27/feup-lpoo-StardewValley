package org.lpoo.g55.controller.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.menus.Menu;

import java.io.IOException;

public abstract class MenuController<T extends Menu> extends Controller<Menu> {
    public MenuController(Menu menu) {
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
            case QUIT:
                game.setState(null);
                break;
            default:
                break;
        }
    }
}
