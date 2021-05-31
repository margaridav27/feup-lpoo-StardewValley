package org.lpoo.g55.viewer.game.elements.animals.barn;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.viewer.game.elements.animals.AnimalViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CowViewer implements AnimalViewer<Cow> {
    private final static Character REPRESENTATION = '}';
    private final static List<String> FOREGROUND = Arrays.asList("#00ffff", "#d7afd7");
    private final static String BACKGROUND = "#af5f00";

    @Override
    public void draw(Cow cow, GUI gui) throws IOException {
        String currentForegound = cow.isReadyToProduce() ? FOREGROUND.get(1) : FOREGROUND.get(0);
        gui.drawElement(cow.getPosition(), REPRESENTATION, currentForegound, BACKGROUND);
    }
}
