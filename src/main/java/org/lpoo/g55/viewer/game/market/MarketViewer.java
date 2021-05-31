package org.lpoo.g55.viewer.game.market;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.market.shops.Shop;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.game.elements.PlayerViewer;
import org.lpoo.g55.viewer.game.market.shops.ShopViewerFactory;

import java.io.IOException;
import java.util.List;

public class MarketViewer extends Viewer<Market> {

    public MarketViewer(Market model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clearGUI();
        gui.drawMarket(getModel());

        drawShops(gui, getModel().getShops());
        drawPlayer(gui, getModel().getPlayer(), new PlayerViewer());

        // TODO IMPROVE this
        gui.drawElement(getModel().getExitPosition(), 'F', "#FFFFFF", null);

        gui.refreshGUI();
    }

    private void drawPlayer(GUI gui, Player player, PlayerViewer viewer) throws IOException {
        viewer.draw(player, gui);
    }

    private void drawShops(GUI gui, List<Shop> shops) {
        for (Shop shop : shops)
            new ShopViewerFactory().draw(shop, gui);
    }
}
