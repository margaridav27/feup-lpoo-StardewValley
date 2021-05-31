package org.lpoo.g55.gui.drawables;

import org.lpoo.g55.model.menus.AnimalShopMenu;
import org.lpoo.g55.model.menus.CropShopMenu;
import org.lpoo.g55.model.menus.InventoryMenu;
import org.lpoo.g55.model.menus.Menu;

import java.io.IOException;

public interface MenuDrawer {

    /**
     * Draws a menu to a custom position
     *
     * @param menu set of options to be drawn
     */
    void drawMenu(Menu menu) throws IOException;

    void drawAnimalShopMenu(AnimalShopMenu menu) throws IOException;

    void drawCropShopMenu(CropShopMenu menu) throws IOException;

    void drawInventoryMenu(InventoryMenu menu);
}
