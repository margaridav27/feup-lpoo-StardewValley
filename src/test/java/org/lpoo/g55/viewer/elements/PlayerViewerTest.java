package org.lpoo.g55.viewer.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.gui.lanternaGUI.LanternaGUI;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.viewer.game.elements.PlayerViewer;
import org.mockito.Mockito;

import java.io.IOException;

public class PlayerViewerTest {
    private GUI gui;
    private Player player;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        player = Mockito.mock(Player.class);

        Mockito.when(player.getPosition()).thenReturn(new Position(1, 1));
    }

    @Test
    void drawTest() throws IOException {
        PlayerViewer playerViewer = new PlayerViewer();
        playerViewer.draw(player, gui);

        Mockito.verify(gui, Mockito.times(1)).drawPlayerName(player);
        Mockito.verify(gui, Mockito.times(1)).drawBalance(player);
        Mockito.verify(gui, Mockito.times(1))
                .drawElement(Mockito.any(Position.class), Mockito.anyChar(), Mockito.anyString(), Mockito.isNull());
    }
}
