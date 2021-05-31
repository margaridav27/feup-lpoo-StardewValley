package org.lpoo.g55.states.menus;

import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.menus.CropMenuController;
import org.lpoo.g55.model.menus.CropMenu;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.menus.CropMenuViewer;

public class CropMenuState extends MenuState<CropMenu> {

    public CropMenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new CropMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new CropMenuController(getModel());
    }
}
