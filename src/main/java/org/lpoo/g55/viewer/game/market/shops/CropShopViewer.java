package org.lpoo.g55.viewer.game.market.shops;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.market.shops.CropShop;

public class CropShopViewer implements ShopViewer<CropShop> {
    public CropShopViewer() {
    }

    @Override
    public void draw(CropShop shop, GUI gui) {
        gui.drawCropShop(shop);
    }
}
