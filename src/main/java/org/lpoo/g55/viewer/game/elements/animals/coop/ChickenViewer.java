package org.lpoo.g55.viewer.game.elements.animals.coop;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.animals.coop.Chicken;
import org.lpoo.g55.viewer.game.elements.animals.AnimalViewer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ChickenViewer implements AnimalViewer<Chicken> {
    private final static Character REPRESENTATION = ')';
    private final static List<String> FOREGROUND = Arrays.asList("#00ffff", "#ff0000");
    private final static String BACKGROUND = "#af8700";

    @Override
    public void draw(Chicken chicken, GUI gui) throws IOException {
        String currentForegound = chicken.isReadyToProduce() ? FOREGROUND.get(1) : FOREGROUND.get(0);
        gui.drawElement(chicken.getPosition(), REPRESENTATION, currentForegound, BACKGROUND);
    }
}
