package org.lpoo.g55.gui.drawables;

import org.lpoo.g55.model.market.shops.AnimalShop;
import org.lpoo.g55.model.market.shops.CropShop;

public interface ShopDrawer {
    void drawCropShop(CropShop shop);
    void drawAnimalShop(AnimalShop shop);
}
