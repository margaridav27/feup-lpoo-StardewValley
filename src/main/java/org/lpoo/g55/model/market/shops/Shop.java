package org.lpoo.g55.model.market.shops;

import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.utils.Position;

public abstract class Shop {
    private final Market MARKET;

    private final String NAME;
    private final Position POSITION;

    public Shop(Market MARKET, String NAME, Position POSITION) {
        this.MARKET = MARKET;
        this.NAME = NAME;
        this.POSITION = POSITION;
    }

    public Market getMarket() {
        return this.MARKET;
    }

    public String getName() {
        return NAME;
    }

    public Position getPosition() {
        return this.POSITION;
    }
}
