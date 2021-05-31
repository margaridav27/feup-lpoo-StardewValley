package org.lpoo.g55.states.menus;

import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.menus.CropShopMenuController;
import org.lpoo.g55.model.menus.CropShopMenu;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.menus.CropShopMenuViewer;

public class CropShopMenuState extends MenuState<CropShopMenu> {
    public CropShopMenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new CropShopMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new CropShopMenuController(getModel());
    }
}
