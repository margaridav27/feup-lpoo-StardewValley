package org.lpoo.g55.commands.farming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.items.products.Product;
import org.lpoo.g55.model.farm.Farm;

public final class CollectCommand implements Command {

    private final static String TEXT = "Collect";

    private final Farm FARM;
    private final Animal ANIMAL;

    public CollectCommand(Farm FARM, Animal ANIMAL) {
        this.FARM = FARM;
        this.ANIMAL = ANIMAL;
    }

    @Override
    public String getText() {
        return CollectCommand.TEXT;
    }

    @Override
    public void execute() {
        if (this.ANIMAL.isReadyToProduce()) {
            Product productionOutcome = this.ANIMAL.produce();
            this.FARM.getPlayer().addItemToInventory(productionOutcome, productionOutcome.getQuantity());
        }
    }
}
