package org.lpoo.g55.gui.drawables;

import org.lpoo.g55.model.market.Market;

public interface MarketDrawer extends ShopDrawer {

    /**
     * Draws the market
     *
     * @param market object to be drawn
     */
    void drawMarket(Market market);
}
