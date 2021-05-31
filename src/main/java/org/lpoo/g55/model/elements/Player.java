package org.lpoo.g55.model.elements;

import org.lpoo.g55.model.elements.items.crops.Crop;
import org.lpoo.g55.model.elements.items.crops.Potato;
import org.lpoo.g55.model.elements.items.crops.Pumpkin;
import org.lpoo.g55.model.elements.items.crops.Wheat;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.elements.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Player extends Element {

    private final String NAME;
    private int balance;
    Map<Item, Integer> inventory;

    public Player(String name, Position position) {
        super(position);

        this.NAME = name;
        this.balance = 4000;
        this.inventory = new HashMap<>();
        initInventory();
    }

    public void initInventory() {
        this.inventory.put(new Pumpkin(null, Crop.CropState.SEED), 4);
        this.inventory.put(new Potato(null, Crop.CropState.SEED), 3);
        this.inventory.put(new Wheat(null, Crop.CropState.SEED), 2);
    }

    public String getName() {
        return this.NAME;
    }

    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Map<Item, Integer> getInventory() {
        return this.inventory;
    }
    public void addItemToInventory(Item itemToAdd, int quantity) {
        if (quantity < 0) return;

        int existent;
        for (Item item : inventory.keySet()) {
            if (item.toString().equals(itemToAdd.toString())) {
                existent = inventory.get(item);
                inventory.put(item, existent + quantity);
                return;
            }
        }
        itemToAdd.setPosition(null);
        inventory.put(itemToAdd, quantity);
    }
    public void removeItemFromInventory(Item itemToRemove, int quantity) {
        if (quantity < 0) return;

        int existent;
        for (Item item : inventory.keySet()) {
            if (item.toString().equals(itemToRemove.toString())) {
                existent = inventory.get(item);
                inventory.put(item, existent - quantity);

                if (inventory.get(item) <= 0)
                    inventory.remove(item);
                break;
            }
        }
    }
}
