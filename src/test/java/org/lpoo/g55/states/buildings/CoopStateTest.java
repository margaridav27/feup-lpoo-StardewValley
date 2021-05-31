package org.lpoo.g55.states.buildings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.states.FarmState;
import org.mockito.Mockito;

public class CoopStateTest {
    @Test
    void stepTest() {
        Game game = new Game();
        Player player = Mockito.mock(Player.class);
        Coop coop = Mockito.mock(Coop.class);

        Mockito.when(player.getPosition()).thenReturn(new Position(10, 10));

        Mockito.when(coop.getPlayer()).thenReturn(player);
        Mockito.when(coop.getExitPosition()).thenReturn(new Position(10, 11));

        CoopState coopState = new CoopState(coop);
        Mockito.when(coopState.getModel().getEntrancePosition()).thenReturn(new Position(10, 11));

        game.setState(coopState);
        coopState.playerStep(game);

        Assertions.assertEquals(CoopState.class, game.getState().getClass());

        Mockito.when(player.getPosition()).thenReturn(new Position(10, 11));
        coopState.playerStep(game);

        Assertions.assertEquals(FarmState.class, game.getState().getClass());
    }
}
