package org.lpoo.g55.model.farm;

import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.model.buildings.BarnBuilder;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.buildings.CoopBuilder;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.market.MarketBuilder;

public class FarmBuilder {
    public Farm buildFarm() {
        Farm farm = new Farm(Size.DEFAULT_WORLD_SIZE);
        Size farmSize = farm.getSize();

        Position initialPlayerPosition = new Position(farmSize.getWidth() / 2 + 2, farmSize.getHeight() / 2 + 2);
        Player player = createPlayer(initialPlayerPosition);

        farm.setPlayer(player);
        farm.setBarn(createBarn(farm));
        farm.setCoop(createCoop(farm));
        farm.setMarket(createMarket(farm));

        farm.setYard(createYard(new Size(30, 15), new Position(10, 16), new Position(23, 16)));

        return farm;
    }

    private Player createPlayer(Position playerPosition) {
        return new Player("PLAYER", playerPosition);
    }

    private Coop createCoop(Farm farm) {
        return new CoopBuilder().buildCoop(farm);
    }

    private Barn createBarn(Farm farm) {
        return new BarnBuilder().buildBarn(farm);
    }

    private Field createYard(Size size, Position topLeftCorner, Position gate) {
        return new Field(size, topLeftCorner, gate);
    }

    private Market createMarket(Farm farm) {
        return new MarketBuilder().buildMarket(farm);
    }
}
