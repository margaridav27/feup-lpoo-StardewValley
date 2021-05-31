package org.lpoo.g55.commands.shopping.items;

import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.elements.items.Item;

import java.util.Map;

public final class RemoveItemFromShoppingChartCommand implements Command {
    private static final String TEXT = "";

    private final Item ITEM;
    private Map<Item, Integer> chart;

    public RemoveItemFromShoppingChartCommand(Item ITEM, Map<Item, Integer> chart) {
        this.ITEM = ITEM;
        this.chart = chart;
    }

    @Override
    public String getText() {
        return RemoveItemFromShoppingChartCommand.TEXT;
    }

    @Override
    public void execute() {
        this.chart.merge(this.ITEM, -1, Integer::sum);
        if (this.chart.get(this.ITEM) < 0)
            this.chart.put(this.ITEM, 0);
    }
}
