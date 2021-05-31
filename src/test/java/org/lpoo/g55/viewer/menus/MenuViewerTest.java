package org.lpoo.g55.viewer.menus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.gui.lanternaGUI.LanternaGUI;
import org.lpoo.g55.model.menus.MainMenu;
import org.lpoo.g55.model.menus.Menu;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuViewerTest {

    private GUI gui;
    private Menu menu;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        menu = Mockito.mock(MainMenu.class);
    }

    @Test
    void drawTest() throws IOException {
        MenuViewer<MainMenu> menuViewer = new MainMenuViewer(menu);
        menuViewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawMenu(menu);
        Mockito.verify(gui, Mockito.times(1)).refreshGUI();
        Mockito.verify(gui, Mockito.times(1)).clearGUI();
    }
}
