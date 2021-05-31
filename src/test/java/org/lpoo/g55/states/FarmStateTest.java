package org.lpoo.g55.states;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.farm.FarmBuilder;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.states.buildings.BarnState;
import org.lpoo.g55.states.buildings.CoopState;

public class FarmStateTest {

    @Test
    void playerStepTest() {
        Game game = new Game();
        Farm farm = new FarmBuilder().buildFarm();

        FarmState farmState = new FarmState(farm);

        Position playerPosition = farm.getBarn().getEntrancePosition();
        farm.getPlayer().setPosition(playerPosition);

        farmState.playerStep(game);
        Assertions.assertEquals(game.getState().getClass(), BarnState.class);
        game.setState(farmState);

        playerPosition = farm.getCoop().getEntrancePosition();
        farm.getPlayer().setPosition(playerPosition);

        farmState.playerStep(game);
        Assertions.assertEquals(game.getState().getClass(), CoopState.class);
        game.setState(farmState);

        playerPosition = farm.getMarket().getEntrancePosition();
        farm.getPlayer().setPosition(playerPosition);

        farmState.playerStep(game);
        Assertions.assertEquals(game.getState().getClass(), MarketState.class);
        game.setState(farmState);

        farmState.playerStep(game);
        farm.getPlayer().setPosition(new Position(5, 5));
        Assertions.assertSame(farmState, game.getState());
    }
}
