package org.lpoo.g55.viewer.buildings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.gui.lanternaGUI.LanternaGUI;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.viewer.game.buildings.CoopViewer;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

public class CoopViewerTest {

    private GUI gui;
    private Coop coop;

    private CoopViewer coopViewer;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        coop = Mockito.mock(Coop.class);
        Mockito.when(coop.getPlayer()).thenReturn(new Player("P", new Position(0, 0)));

        coopViewer = new CoopViewer(coop);
    }

    @Test
    void drawTest() throws IOException {
        Mockito.when(coop.getAnimals()).thenReturn(new ArrayList<>());

        coopViewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).refreshGUI();
        Mockito.verify(gui, Mockito.times(1)).clearGUI();
    }

    @Test
    void drawOutsideTest() {
        Mockito.when(coop.getEntrancePosition()).thenReturn(new Position(2, 2));
        coopViewer.drawOutside(gui);
        Mockito.verify(gui, Mockito.times(1)).drawCoopOutside(coop.getEntrancePosition());
    }
}
