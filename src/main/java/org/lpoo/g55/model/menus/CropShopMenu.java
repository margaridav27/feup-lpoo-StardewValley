package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.shopping.LeaveShopCommand;
import org.lpoo.g55.commands.shopping.items.AddItemToShoppingChartCommand;
import org.lpoo.g55.commands.shopping.items.ShopItemCheckoutCommand;
import org.lpoo.g55.commands.shopping.items.RemoveItemFromShoppingChartCommand;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.elements.items.crops.*;
import org.lpoo.g55.model.market.shops.Shop;
import org.lpoo.g55.model.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CropShopMenu extends ShopMenu {

    private final static String BACKGROUND = "#000000";
    private final static String FOREGROUND = "#FFFFFF";

    private Map<Item, Integer> chart;

    public CropShopMenu(Game game, Farm farm, Shop shop, Position topLeftCorner) {
        super(game, farm, shop, topLeftCorner);

        this.initShoppingChart();
        this.initEntries();
    }

    @Override
    protected void initShoppingChart() {
        this.chart = new HashMap<>();

        // crops empty slots
        this.chart.put(new Pumpkin(null, Crop.CropState.SEED), 0);
        this.chart.put(new Potato(null, Crop.CropState.SEED), 0);
        this.chart.put(new Cauliflower(null, Crop.CropState.SEED), 0);
        this.chart.put(new Wheat(null, Crop.CropState.SEED), 0);
    }
    @Override
    protected void initEntries() {
        this.entries = new ArrayList<>();

        for (Item item : this.chart.keySet()) {
            this.entries.add(new AddItemToShoppingChartCommand(item, this.chart));
            this.entries.add(new RemoveItemFromShoppingChartCommand(item, this.chart));
        }

        this.entries.add(new ShopItemCheckoutCommand(game, farm, this.chart));
        this.entries.add(new LeaveShopCommand(game, farm));
    }

    public Map<Item, Integer> getChart() {
        return this.chart;
    }

    @Override
    public void nextEntry() {
        if (selectedEntry == entries.size() - 2) selectedEntry += 1;
        else selectedEntry += 2;
        selectedEntry = Math.min(selectedEntry, entries.size() - 1);
    }
    @Override
    public void previousEntry() {
        if (selectedEntry == entries.size() - 1) selectedEntry -= 1;
        else selectedEntry -= 2;
        selectedEntry = Math.max(selectedEntry, 0);
    }

    @Override
    public String getBackground() {
        return CropShopMenu.BACKGROUND;
    }
    @Override
    public String getForeground() {
        return CropShopMenu.FOREGROUND;
    }
}
