package org.lpoo.g55.commands.gaming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.QuitCommand;
import org.lpoo.g55.model.farm.Farm;
import org.mockito.Mockito;

public class QuitCommandTest {
    @Test
    void executeTest() {
        Game game = new Game();
        Farm farm = Mockito.mock(Farm.class);

        QuitCommand quitCommand = new QuitCommand(game);
        quitCommand.execute();

        Assertions.assertNull(game.getState());
    }
}
