package org.lpoo.g55.commands.shopping.items;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.commands.shopping.LeaveShopCommand;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.farm.Farm;

import java.io.IOException;
import java.util.Map;

public final class ShopItemCheckoutCommand implements Command {
    private final static String TEXT = "Checkout";

    private final Game GAME;
    private final Farm FARM;
    private Map<Item, Integer> chart;

    public ShopItemCheckoutCommand(Game GAME, Farm FARM, Map<Item, Integer> chart) {
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

    private boolean hasFinancialCapacity() {
        return getTotalAmount() < this.FARM.getPlayer().getBalance();
    }

    @Override
    public String getText() {
        return ShopItemCheckoutCommand.TEXT;
    }

    @Override
    public void execute() throws IOException {
        if (hasFinancialCapacity()) {
            for (Item item : this.chart.keySet()) {
                int purchaseQuantity = this.chart.get(item);
                if (purchaseQuantity == 0) continue;
                this.FARM.getPlayer().addItemToInventory(item, purchaseQuantity);
            }

            int updatedBalance = this.FARM.getPlayer().getBalance() - getTotalAmount();
            this.FARM.getPlayer().setBalance(updatedBalance);
            new LeaveShopCommand(this.GAME, this.FARM).execute();
        }
    }
}
