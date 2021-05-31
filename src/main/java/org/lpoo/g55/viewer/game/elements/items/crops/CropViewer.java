package org.lpoo.g55.viewer.game.elements.items.crops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.items.crops.Crop;
import org.lpoo.g55.viewer.game.elements.ElementViewer;

import java.io.IOException;

public interface CropViewer<T extends Crop> extends ElementViewer<T> {
    @Override
    void draw(T element, GUI gui) throws IOException;
}
