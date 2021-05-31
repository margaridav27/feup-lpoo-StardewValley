package org.lpoo.g55.commands.gaming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.states.FarmState;

public class StartCommandTest {
    @Test
    void executeTest() {
        Game game = new Game();

        StartCommand startCommand = new StartCommand(game);
        startCommand.execute();

        Assertions.assertEquals(FarmState.class, game.getState().getClass());
    }
}
