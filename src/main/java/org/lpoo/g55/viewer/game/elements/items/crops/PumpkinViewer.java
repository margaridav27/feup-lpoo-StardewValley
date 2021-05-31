package org.lpoo.g55.viewer.game.elements.items.crops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.items.crops.Pumpkin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PumpkinViewer implements CropViewer<Pumpkin> {
    private final static Character REPRESENTATION = '+';
    private final static List<String> FOREGROUND = Arrays.asList("#00D154", "#BBCC5E", "#B89300", "#1C1C1A", "#666666");
    private final static String BACKGROUND = "#7A623B";

    @Override
    public void draw(Pumpkin pumpkin, GUI gui) throws IOException {
        String currentForeground = FOREGROUND.get(pumpkin.getState().ordinal());
        gui.drawElement(pumpkin.getPosition(), REPRESENTATION, currentForeground, BACKGROUND);
    }
}
