package org.lpoo.g55.viewer.game.market.shops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.market.shops.AnimalShop;

public class AnimalShopViewer implements ShopViewer<AnimalShop> {
    public AnimalShopViewer() {
    }

    @Override
    public void draw(AnimalShop shop, GUI gui) {
        gui.drawAnimalShop(shop);
    }
}
