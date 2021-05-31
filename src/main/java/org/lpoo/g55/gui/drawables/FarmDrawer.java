package org.lpoo.g55.gui.drawables;

import org.lpoo.g55.model.farm.Field;
import org.lpoo.g55.model.farm.Farm;

public interface FarmDrawer extends BuildingDrawer {

    /**
     * Draws the Farm
     *
     * @param farm farm to be drawn
     */
    void drawFarm(Farm farm);

    /**
     * Draws a field in a custom position of the screen
     *
     * @param field object to be drawn
     */
    void drawField(Field field);
}
