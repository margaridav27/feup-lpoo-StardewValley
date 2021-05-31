package org.lpoo.g55.gui.drawables;

import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.utils.Position;

public interface BuildingDrawer {

    /**
     * Draws a barn from the inside
     *
     * @param coop object to be drawn
     */
    void drawCoop(Coop coop);

    /**
     * Draws the coop in a custom position of the screen
     *
     * @param position custom position telling where coop should be printed
     */
    void drawCoopOutside(Position position);

    /**
     * Draws a barn from the inside
     *
     * @param barn object to be drawn
     */
    void drawBarn(Barn barn);

    /**
     * Draws the barn in a custom position of the screen
     *
     * @param position custom position telling where barn should be printed
     */
    void drawBarnOutside(Position position);
}
