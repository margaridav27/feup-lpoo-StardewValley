package org.lpoo.g55.viewer.game.elements.items.crops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.items.crops.*;

import java.io.IOException;

public class CropViewerFactory {
    public void draw(Crop crop, GUI gui) throws IOException {
        if (crop instanceof Cauliflower) new CauliflowerViewer().draw((Cauliflower) crop, gui);
        if (crop instanceof Potato) new PotatoViewer().draw((Potato) crop, gui);
        if (crop instanceof Pumpkin) new PumpkinViewer().draw((Pumpkin) crop, gui);
        if (crop instanceof Wheat) new WheatViewer().draw((Wheat) crop, gui);
    }
}
