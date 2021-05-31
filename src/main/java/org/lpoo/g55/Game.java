package org.lpoo.g55;

import com.googlecode.lanterna.TerminalSize;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.gui.lanternaGUI.LanternaGUI;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.farm.FarmBuilder;
import org.lpoo.g55.model.menus.MainMenu;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.states.State;
import org.lpoo.g55.states.menus.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private GUI gui;
    private State<?> state;

    public State<?> getState() {
        return state;
    }
    public void setState(State<?> nextState) {
        state = nextState;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().startGame();
    }

    private void startGame() throws IOException, URISyntaxException, FontFormatException {
        Size guiSize = new Size(Size.DEFAULT_WORLD_SIZE.getWidth() + 6, Size.DEFAULT_WORLD_SIZE.getHeight() + 6);
        gui = new LanternaGUI(new TerminalSize(guiSize.getWidth(), guiSize.getHeight()));

        Farm farm = new FarmBuilder().buildFarm();
        MainMenu startMenu = new MainMenu(this, farm, new Position(guiSize.getWidth() / 2 - 6, guiSize.getHeight() / 2 - 6));
        state = new MainMenuState(startMenu);

        loop();
    }

    private void loop() throws IOException {
        final int FPS = 30;
        final int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                Thread.sleep(Math.max(0, sleepTime));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        gui.closeGUI();
    }

    public GUI getGui() {
        return this.gui;
    }
}
