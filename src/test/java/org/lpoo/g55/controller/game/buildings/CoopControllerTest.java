package org.lpoo.g55.controller.game.buildings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.lpoo.g55.Game;
import org.lpoo.g55.controller.game.elements.AnimalController;
import org.lpoo.g55.controller.game.elements.PlayerController;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.farm.Farm;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Random;

import static org.mockito.ArgumentMatchers.eq;

public class CoopControllerTest {
    private Game game;

    private PlayerController<?> playerController;
    private AnimalController<?> animalController;
    private CoopController coopController;

    private Random timeValuesGenerator;

    @BeforeEach
    void setUp() {
        timeValuesGenerator = new Random();

        playerController = Mockito.mock(PlayerController.class);
        Mockito.doNothing().when(playerController).step(Mockito.any(), Mockito.any(), Mockito.anyLong());
        animalController = Mockito.mock(AnimalController.class);
        Mockito.doNothing().when(animalController).step(Mockito.any(), Mockito.any(), Mockito.anyLong());

        game = Mockito.mock(Game.class);

        Farm farm = Mockito.mock(Farm.class);
        Coop coop = Mockito.mock(Coop.class);

        Mockito.when(farm.getCoop()).thenReturn(coop);
        Mockito.when(coop.getAnimals()).thenReturn(new ArrayList<>());

        coopController = new CoopController(farm.getCoop());
    }

    @Test
    void stepNull() {
        GUI.ACTION action = GUI.ACTION.QUIT;
        long time = timeValuesGenerator.nextLong();

        coopController.step(game, action, time);
        Mockito.verify(playerController, Mockito.times(0)).step(eq(game), eq(action), Mockito.anyLong());
        Mockito.verify(animalController, Mockito.times(0)).step(eq(game), eq(action), Mockito.anyLong());
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }
}
