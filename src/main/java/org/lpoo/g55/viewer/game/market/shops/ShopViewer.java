package org.lpoo.g55.viewer.game.market.shops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.market.shops.Shop;

public interface ShopViewer<T extends Shop> {
    void draw(T shop, GUI gui);
}
