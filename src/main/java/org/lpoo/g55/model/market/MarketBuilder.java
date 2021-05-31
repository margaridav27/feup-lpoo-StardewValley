package org.lpoo.g55.model.market;

import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.market.shops.AnimalShop;
import org.lpoo.g55.model.market.shops.CropShop;
import org.lpoo.g55.model.market.shops.Shop;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

import java.util.ArrayList;
import java.util.List;

public class MarketBuilder {
    public Market buildMarket(Farm farm) {
        Position marketEntrance = new Position(50, 20);
        Position marketExit = new Position(5, 20);
        Market market = new Market(Size.DEFAULT_WORLD_SIZE, marketEntrance, marketExit);

        market.setFarm(farm);
        market.setPlayer(farm.getPlayer());

        List<Shop> shops = new ArrayList<>();
        shops.add(new CropShop(market, "Armindo's Crop Shop", new Position(10, 10)));
        shops.add(new AnimalShop(market, "Ernesto's Animal Shop", new Position(30, 15)));

        market.setShops(shops);
        return market;
    }
}
