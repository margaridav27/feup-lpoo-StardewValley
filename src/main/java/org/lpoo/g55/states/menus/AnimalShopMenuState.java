package org.lpoo.g55.states.menus;

import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.menus.AnimalShopMenuController;
import org.lpoo.g55.model.menus.AnimalShopMenu;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.menus.AnimalShopMenuViewer;

public class AnimalShopMenuState extends MenuState<AnimalShopMenu> {
    public AnimalShopMenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new AnimalShopMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new AnimalShopMenuController(getModel());
    }
}
