package org.lpoo.g55.states.menus;

import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.menus.MainMenuController;
import org.lpoo.g55.model.menus.MainMenu;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.viewer.menus.MainMenuViewer;
import org.lpoo.g55.viewer.Viewer;

public class MainMenuState extends MenuState<MainMenu> {

    public MainMenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MainMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MainMenuController(getModel());
    }
}
