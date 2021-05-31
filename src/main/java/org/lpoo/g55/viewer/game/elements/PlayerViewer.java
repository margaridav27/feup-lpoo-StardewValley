package org.lpoo.g55.viewer.game.elements;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.viewer.game.elements.ElementViewer;

import java.io.IOException;

public class PlayerViewer implements ElementViewer<Player> {
    private final static Character REPRESENTATION = '(';
    private final static String FOREGROUND = "#ffffff";

    @Override
    public void draw(Player player, GUI gui) throws IOException {
        gui.drawPlayerName(player);
        gui.drawBalance(player);
        gui.drawElement(player.getPosition(), REPRESENTATION, FOREGROUND, null);
    }
}