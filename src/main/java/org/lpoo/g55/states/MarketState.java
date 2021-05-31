package org.lpoo.g55.states;

import org.lpoo.g55.Game;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.game.MarketController;
import org.lpoo.g55.controller.game.elements.PlayerController;
import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.market.shops.AnimalShop;
import org.lpoo.g55.model.market.shops.Shop;
import org.lpoo.g55.model.menus.AnimalShopMenu;
import org.lpoo.g55.model.menus.CropShopMenu;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.states.menus.AnimalShopMenuState;
import org.lpoo.g55.states.menus.CropShopMenuState;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.game.market.MarketViewer;

public class MarketState extends State<Market> implements PlayerInteraction {
    public MarketState(Market model) {
        super(model);
    }

    @Override
    protected Viewer<Market> getViewer() {
        return new MarketViewer(getModel());
    }

    @Override
    protected Controller<Market> getController() {
        return new MarketController(getModel(), new PlayerController<>(getModel()));
    }

    @Override
    public void playerStep(Game game) {
        Position playerPosition = getModel().getPlayer().getPosition();

        if (playerPosition.equals(getModel().getExitPosition())) {
            Position playerExitPosition = getModel().getEntrancePosition().getLeft();
            getModel().getPlayer().setPosition(playerExitPosition);

            game.setState(new FarmState(getModel().getFarm()));
        }

        for (Shop shop : getModel().getShops()) {
            if (playerPosition.equals(shop.getPosition())) {
                if (shop instanceof AnimalShop)
                    game.setState(new AnimalShopMenuState(new AnimalShopMenu(game, getModel().getFarm(), shop, new Position(0,0))));
                else
                    game.setState(new CropShopMenuState(new CropShopMenu(game, getModel().getFarm(), shop, new Position(0,0))));
            }
        }
    }
}
