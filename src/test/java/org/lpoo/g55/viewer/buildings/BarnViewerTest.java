package org.lpoo.g55.viewer.buildings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.gui.lanternaGUI.LanternaGUI;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.viewer.game.buildings.BarnViewer;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

public class BarnViewerTest {

    private GUI gui;
    private Barn barn;

    private BarnViewer barnViewer;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        barn = Mockito.mock(Barn.class);
        Mockito.when(barn.getPlayer()).thenReturn(new Player("P", new Position(0, 0)));

        barnViewer = new BarnViewer(barn);
    }

    @Test
    void drawTest() throws IOException {
        Mockito.when(barn.getAnimals()).thenReturn(new ArrayList<>());
        barnViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).refreshGUI();
        Mockito.verify(gui, Mockito.times(1)).clearGUI();
    }

    @Test
    void drawOutsideTest() {
        Mockito.when(barn.getEntrancePosition()).thenReturn(new Position(2, 2));
        barnViewer.drawOutside(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBarnOutside(barn.getEntrancePosition());
    }
}
