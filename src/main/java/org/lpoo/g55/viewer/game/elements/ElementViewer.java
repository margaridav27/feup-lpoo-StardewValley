package org.lpoo.g55.viewer.game.elements;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.Element;

import java.io.IOException;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui) throws IOException;
}