package org.lpoo.g55.commands.gaming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.InstructionsCommand;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.states.menus.InstructionsMenuState;
import org.mockito.Mockito;

public class InstructionsCommandTest {
    @Test
    void executeTest() {
        Game game = new Game();
        Farm farm = Mockito.mock(Farm.class);

        InstructionsCommand instructionsCommand = new InstructionsCommand(game, farm);
        instructionsCommand.execute();

        Assertions.assertNotNull(game.getState());
        Assertions.assertEquals(InstructionsMenuState.class, game.getState().getClass());
    }
}
