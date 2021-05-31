package org.lpoo.g55.commands.shopping.animals;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.commands.shopping.LeaveShopCommand;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.animals.barn.BarnAnimal;
import org.lpoo.g55.model.elements.animals.coop.CoopAnimal;

import java.util.Map;

public final class ShopAnimalCheckoutCommand implements Command {
    private final static String TEXT = "Checkout";

    private final Game GAME;
    private final Farm FARM;
    private Map<Animal, Integer> chart;

    public ShopAnimalCheckoutCommand(Game GAME, Farm FARM, Map<Animal, Integer> chart) {
        this.GAME = GAME;
        this.FARM = FARM;
        this.chart = chart;
    }

    private int getTotalOrderAmount() {
        int totalAmount = 0;
        for (Animal animal : this.chart.keySet())
            totalAmount += animal.getPrice() * this.chart.get(animal);
        return totalAmount;
    }

    private boolean hasFinancialCapacity() {
        return getTotalOrderAmount() < this.FARM.getPlayer().getBalance();
    }

    @Override
    public void execute() {

        if (hasFinancialCapacity()) {
            boolean buildingCapacityExceeded = false;

            for (Animal animal : this.chart.keySet()) {
                int purchaseQuantity = this.chart.get(animal);

                if (animal instanceof BarnAnimal) {
                    if (this.FARM.getBarn().hasCapacity(purchaseQuantity))
                        this.FARM.getBarn().addAnimal((BarnAnimal) animal, purchaseQuantity);
                    else buildingCapacityExceeded = true;
                } else if (animal instanceof CoopAnimal) {
                    if (this.FARM.getCoop().hasCapacity(purchaseQuantity))
                        this.FARM.getCoop().addAnimal((CoopAnimal) animal, purchaseQuantity);
                    else buildingCapacityExceeded = true;
                }
            }

            if (!buildingCapacityExceeded) {
                int updatedBalance = this.FARM.getPlayer().getBalance() - getTotalOrderAmount();
                this.FARM.getPlayer().setBalance(updatedBalance);
                new LeaveShopCommand(this.GAME, this.FARM).execute();
            }
        }
    }

    @Override
    public String getText() {
        return ShopAnimalCheckoutCommand.TEXT;
    }
}
