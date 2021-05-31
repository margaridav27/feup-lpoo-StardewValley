package org.lpoo.g55.viewer.game.elements.animals;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.viewer.game.elements.ElementViewer;

import java.io.IOException;

public interface AnimalViewer<T extends Animal> extends ElementViewer<T> {
    @Override
    void draw(T animal, GUI gui) throws IOException;
}
