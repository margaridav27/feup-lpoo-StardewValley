package org.lpoo.g55.commands.shopping.items;

import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.elements.items.Item;

import java.util.Map;

public final class AddItemToShoppingChartCommand implements Command {
    private final String TEXT;

    private final Item ITEM;
    private Map<Item, Integer> chart;

    public AddItemToShoppingChartCommand(Item ITEM, Map<Item, Integer> chart) {
        this.ITEM = ITEM;
        this.chart = chart;
        this.TEXT = this.ITEM.toString();
    }

    @Override
    public String getText() {
        return this.TEXT;
    }

    @Override
    public void execute() {
        this.chart.merge(this.ITEM, 1, Integer::sum);
    }
}
