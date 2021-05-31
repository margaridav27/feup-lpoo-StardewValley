package org.lpoo.g55.viewer.game.elements.animals.coop;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.animals.coop.Bunny;
import org.lpoo.g55.viewer.game.elements.animals.AnimalViewer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BunnyViewer implements AnimalViewer<Bunny> {
    private final static Character REPRESENTATION = '|';
    private final static List<String> FOREGROUND = Arrays.asList("#00ffff", "#ff0000");
    private final static String BACKGROUND = "#af8700";

    @Override
    public void draw(Bunny bunny, GUI gui) throws IOException {
        String currentForegound = bunny.isReadyToProduce() ? FOREGROUND.get(1) : FOREGROUND.get(0);
        gui.drawElement(bunny.getPosition(), REPRESENTATION, currentForegound, BACKGROUND);
    }
}
