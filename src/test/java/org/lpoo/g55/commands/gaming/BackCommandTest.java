package org.lpoo.g55.commands.gaming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.BackToFarmCommand;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.states.FarmState;
import org.mockito.Mockito;

public class BackCommandTest {
    @Test
    void executeTest() {
        Game game = new Game();
        Farm farm = Mockito.mock(Farm.class);

        BackToFarmCommand backCommand = new BackToFarmCommand(game, farm);
        backCommand.execute();

        Assertions.assertNotNull(game.getState());
        Assertions.assertEquals(FarmState.class, game.getState().getClass());
    }
}
