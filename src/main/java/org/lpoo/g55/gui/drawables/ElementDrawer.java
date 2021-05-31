package org.lpoo.g55.gui.drawables;

import org.lpoo.g55.model.utils.Position;

public interface ElementDrawer {

    /**
     * Draws an element in a custom position of the screen
     *
     * @param position       custom position where the element should be drawn
     * @param representation custom element's representation
     */
    void drawElement(Position position, Character representation, String foreground, String background);
}
