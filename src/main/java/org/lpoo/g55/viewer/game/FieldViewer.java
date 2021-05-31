package org.lpoo.g55.viewer.game;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.farm.Field;
import org.lpoo.g55.viewer.Viewer;

import java.io.IOException;

public class FieldViewer extends Viewer<Field> {

    public FieldViewer(Field model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.drawField(getModel());
    }
}
