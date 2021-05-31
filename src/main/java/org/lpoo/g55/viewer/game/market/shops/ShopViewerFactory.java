package org.lpoo.g55.viewer.game.market.shops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.market.shops.AnimalShop;
import org.lpoo.g55.model.market.shops.CropShop;
import org.lpoo.g55.model.market.shops.Shop;

public class ShopViewerFactory {
    public void draw(Shop shop, GUI gui) {
        if (shop instanceof CropShop) new CropShopViewer().draw((CropShop) shop, gui);
        if (shop instanceof AnimalShop) new AnimalShopViewer().draw((AnimalShop) shop, gui);
    }
}
