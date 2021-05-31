package org.lpoo.g55.commands.shopping.items;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.commands.gaming.BackToFarmCommand;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.farm.Farm;

import java.io.IOException;
import java.util.Map;

public final class SellItemCheckoutCommand implements Command {
    private final static String TEXT = "Sell";

    private final Game GAME;
    private final Farm FARM;
    private Map<Item, Integer> chart;

    public SellItemCheckoutCommand(Game GAME, Farm FARM, Map<Item, Integer> chart) {
        this.GAME = GAME;
        this.FARM = FARM;
        this.chart = chart;
    }

    private int getTotalAmount() {
        int totalAmount = 0;
        for (Item item : this.chart.keySet())
            totalAmount += item.getPrice() * this.chart.get(item);
        return totalAmount;
    }

    @Override
    public String getText() {
        return SellItemCheckoutCommand.TEXT;
    }

    @Override
    public void execute() throws IOException {
        for (Item item : chart.keySet()) {
            FARM.getPlayer().removeItemFromInventory(item, chart.get(item));
        }

        int updatedBalance = this.FARM.getPlayer().getBalance() + getTotalAmount();
        this.FARM.getPlayer().setBalance(updatedBalance);
        new BackToFarmCommand(GAME, FARM).execute();
    }
}
