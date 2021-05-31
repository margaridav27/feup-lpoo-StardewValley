package org.lpoo.g55.gui.drawables;

import com.googlecode.lanterna.TerminalPosition;
import org.lpoo.g55.model.utils.Position;

import java.io.IOException;

public interface DialogDrawer {

    /**
     * Draws a message to inform the player
     *
     * @param message Message to be drawn
     */
    public void drawDialog(String message, TerminalPosition lanternaPosition) throws IOException;
}
