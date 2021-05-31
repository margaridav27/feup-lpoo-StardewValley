package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.market.shops.Shop;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

public abstract class ShopMenu extends Menu {
    private final Shop SHOP;

    private final String TITLE;

    public ShopMenu(Game game, Farm farm, Shop shop, Position topLeftCorner) {
        super(game, farm, topLeftCorner);
        this.size = new Size(30, 30);
        this.SHOP = shop;
        this.TITLE = this.SHOP.getName();
    }

    protected abstract void initShoppingChart();

    protected abstract void initEntries();

    public Shop getShop() {
        return this.SHOP;
    }

    @Override
    public String getTitle() {
        return this.TITLE;
    }

    @Override
    public String getBackground() {
        return null;
    }

    @Override
    public String getForeground() {
        return null;
    }
}
