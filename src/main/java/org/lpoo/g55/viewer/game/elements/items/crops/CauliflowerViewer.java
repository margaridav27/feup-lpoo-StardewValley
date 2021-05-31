package org.lpoo.g55.viewer.game.elements.items.crops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.items.crops.Cauliflower;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CauliflowerViewer implements CropViewer<Cauliflower> {
    private final static Character REPRESENTATION = '$';
    private final static List<String> FOREGROUND = Arrays.asList("#00D154", "#BBCC5E", "#B89300", "#1C1C1A", "#666666");
    private final static String BACKGROUND = "#7A623B";

    @Override
    public void draw(Cauliflower cauliflower, GUI gui) throws IOException {
        String currentForeground = FOREGROUND.get(cauliflower.getState().ordinal());
        gui.drawElement(cauliflower.getPosition(), REPRESENTATION, currentForeground, BACKGROUND);
    }
}
