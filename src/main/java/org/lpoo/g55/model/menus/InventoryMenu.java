package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.shopping.items.*;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InventoryMenu extends Menu {

    private final static String TITLE = "Inventory";

    private final static String BACKGROUND = "#000000";
    private final static String FOREGROUND = "#FFFFFF";

    private final Map<Item, Integer> INVENTORY;
    private Map<Item, Integer> chart;

    public InventoryMenu(Game game, Farm farm, Position topLeftCorner) {
        super(game, farm, topLeftCorner);

        this.size = new Size(30, 30);
        this.INVENTORY = farm.getPlayer().getInventory();

        this.initChart();
        this.initEntries();
    }

    private void initChart() {
        this.chart = new HashMap<>();

        if (INVENTORY.isEmpty()) return;

        for (Item item : INVENTORY.keySet())
            this.chart.put(item, 0);
    }
    private void initEntries() {
        this.entries = new ArrayList<>();

        if (chart.isEmpty()) return;

        for (Item item : chart.keySet()) {
            this.entries.add(new AddToSellingChartCommand(farm, item, chart));
            this.entries.add(new RemoveFromSellingChartCommand(item, chart));
        }
        this.entries.add(new SellItemCheckoutCommand(game, farm, chart));
    }

    public Map<Item, Integer> getChart() {
        return this.chart;
    }

    public Map<Item, Integer> getInventory() {
        return this.INVENTORY;
    }

    @Override
    public void nextEntry() {
        selectedEntry += 2;
        selectedEntry = Math.min(selectedEntry, entries.size() - 1);
    }
    @Override
    public void previousEntry() {
        selectedEntry -= 2;
        selectedEntry = Math.max(selectedEntry, 0);
    }

    @Override
    public String getTitle() {
        return InventoryMenu.TITLE;
    }
    @Override
    public String getBackground() {
        return InventoryMenu.BACKGROUND;
    }
    @Override
    public String getForeground() {
        return InventoryMenu.FOREGROUND;
    }
}
