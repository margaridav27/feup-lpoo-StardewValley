package org.lpoo.g55.states;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.Game;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.market.shops.AnimalShop;
import org.lpoo.g55.model.market.shops.Shop;
import org.lpoo.g55.model.utils.Position;
import org.mockito.Mockito;

import java.util.List;
import java.util.ArrayList;

public class MarketStateTest {

    @Test
    void playerStepTest() {
        Game game = new Game();
        Player player = new Player(null, new Position(10, 10));
        Market market = Mockito.mock(Market.class);

        List<Shop> shops = new ArrayList<>();
        shops.add(new AnimalShop(null, null, new Position(4, 4)));
        Mockito.when(market.getShops()).thenReturn(shops);
        Mockito.when(market.getPlayer()).thenReturn(player);
        Mockito.when(market.getEntrancePosition()).thenReturn(new Position(9, 0));
        Mockito.when(market.getExitPosition()).thenReturn(new Position(7, 7));

        MarketState marketState = new MarketState(market);

        Position playerPosition = market.getExitPosition();
        market.getPlayer().setPosition(playerPosition);

        marketState.playerStep(game);
        Assertions.assertEquals(game.getState().getClass(), FarmState.class);
        Assertions.assertEquals(player.getPosition(), new Position(8, 0));
        game.setState(marketState);

        playerPosition = market.getShops().get(0).getPosition();
        market.getPlayer().setPosition(playerPosition);

        marketState.playerStep(game);
        Assertions.assertNotEquals(game.getState().getClass(), MarketState.class);
        game.setState(marketState);

        market.getPlayer().setPosition(new Position(5, 5));
        marketState.playerStep(game);
        Assertions.assertSame(marketState, game.getState());
    }
}
