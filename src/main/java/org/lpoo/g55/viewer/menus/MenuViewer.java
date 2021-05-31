package org.lpoo.g55.viewer.menus;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.viewer.Viewer;

import java.io.IOException;

public abstract class MenuViewer<T extends Menu> extends Viewer<Menu> {

    public MenuViewer(Menu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clearGUI();
        gui.drawMenu(getModel());
        gui.refreshGUI();
    }
}
