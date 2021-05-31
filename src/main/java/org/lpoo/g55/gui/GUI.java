package org.lpoo.g55.gui;

import java.io.IOException;

public interface GUI extends Drawable {

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, BACKSPACE, CHAR_I, CHAR_C, CHAR_W, CHAR_H, ESC}
    ACTION getNextAction() throws IOException;

    /**
     * Clears all the contents of the GUI
     */
    void clearGUI();

    /**
     * Replaces the GUI with a the new contents present in an auxiliary buffer
     *
     * @throws IOException if there was an underlying I/O error
     */
    void refreshGUI() throws IOException;

    /**
     * Closes the GUI and frees any system's resource in use
     *
     * @throws IOException if there was an underlying I/O error
     */
    void closeGUI() throws IOException;
}
