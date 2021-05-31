package org.lpoo.g55.controller.game.buildings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.controller.game.elements.AnimalController;
import org.lpoo.g55.controller.game.elements.PlayerController;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.states.buildings.BarnState;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;

public class BarnControllerTest {
    private Game game;

    private PlayerController<?> playerController;
    private AnimalController<?> animalController;
    private Barn barn;

    private BarnController barnController;


    @BeforeEach
    void setUp() {
        playerController = Mockito.mock(PlayerController.class);
        Mockito.doNothing().when(playerController)
                .step(Mockito.any(Game.class), Mockito.any(GUI.ACTION.class), Mockito.anyLong());
        animalController = Mockito.mock(AnimalController.class);
        Mockito.doNothing().when(animalController)
                .step(Mockito.any(Game.class), Mockito.any(GUI.ACTION.class), Mockito.anyLong());

        game = new Game();
        barn = Mockito.mock(Barn.class);

        barnController = new BarnController(barn);
    }

    @Test
    void stepNull() {
        GUI.ACTION action = GUI.ACTION.QUIT;
        BarnState barnState = new BarnState(barn);
        game.setState(barnState);

        barnController.step(game, action, 1000);
        Mockito.verify(playerController, Mockito.times(0)).step(eq(game), eq(action), Mockito.anyLong());
        Mockito.verify(animalController, Mockito.times(0)).step(eq(game), eq(action), Mockito.anyLong());
        Assertions.assertNull(game.getState());
    }
}
