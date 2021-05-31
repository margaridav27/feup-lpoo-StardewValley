package org.lpoo.g55.states.menus;

import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.menus.InstructionsMenuController;
import org.lpoo.g55.model.menus.InstructionsMenu;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.menus.InstructionsMenuViewer;

public class InstructionsMenuState extends MenuState<InstructionsMenu> {

    public InstructionsMenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new InstructionsMenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new InstructionsMenuController(getModel());
    }
}
