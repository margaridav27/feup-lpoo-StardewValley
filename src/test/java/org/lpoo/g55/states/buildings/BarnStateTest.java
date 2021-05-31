package org.lpoo.g55.states.buildings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.states.FarmState;
import org.mockito.Mockito;

public class BarnStateTest {
    @Test
    void stepTest() {
        Game game = new Game();
        Player player = Mockito.mock(Player.class);
        Barn barn = Mockito.mock(Barn.class);

        Mockito.when(player.getPosition()).thenReturn(new Position(10, 10));

        Mockito.when(barn.getPlayer()).thenReturn(player);
        Mockito.when(barn.getExitPosition()).thenReturn(new Position(10, 11));

        BarnState barnState = new BarnState(barn);
        Mockito.when(barnState.getModel().getEntrancePosition()).thenReturn(new Position(10, 11));

        game.setState(barnState);
        barnState.playerStep(game);

        Assertions.assertEquals(BarnState.class, game.getState().getClass());

        Mockito.when(player.getPosition()).thenReturn(new Position(10, 11));
        barnState.playerStep(game);

        Assertions.assertEquals(FarmState.class, game.getState().getClass());
    }
}
