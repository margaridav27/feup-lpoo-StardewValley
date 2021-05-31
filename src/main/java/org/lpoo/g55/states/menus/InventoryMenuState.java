package org.lpoo.g55.states.menus;

import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.menus.InventoryMenuController;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.menus.InventoryMenu;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.menus.InventoryMenuViewer;

public class InventoryMenuState extends MenuState<InventoryMenu> {

    public InventoryMenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new InventoryMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new InventoryMenuController(getModel(), getModel().getFarm());
    }
}
