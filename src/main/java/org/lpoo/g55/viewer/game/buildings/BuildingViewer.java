package org.lpoo.g55.viewer.game.buildings;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Building;

import java.io.IOException;

public interface BuildingViewer<T extends Building> {
    void drawOutside(GUI gui) throws IOException;
}
