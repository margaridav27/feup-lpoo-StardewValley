package org.lpoo.g55.commands.shopping.items;

import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.farm.Farm;

import java.util.Map;

public class AddToSellingChartCommand implements Command {
    private final String TEXT;

    private final Item ITEM;
    private Map<Item, Integer> chart;
    private Map<Item, Integer> available;

    public AddToSellingChartCommand(Farm farm, Item ITEM, Map<Item, Integer> chart) {
        this.ITEM = ITEM;
        this.available = farm.getPlayer().getInventory();
        this.chart = chart;
        this.TEXT = this.ITEM.toString();
    }

    private boolean hasQuantityAvailable() {
        return (this.available.get(this.ITEM) > this.chart.get(this.ITEM));
    }

    @Override
    public String getText() {
        return this.TEXT;
    }

    @Override
    public void execute() {
        if (hasQuantityAvailable())
            this.chart.merge(this.ITEM, 1, Integer::sum);
    }
}
