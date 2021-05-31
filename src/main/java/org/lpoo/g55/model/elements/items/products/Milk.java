package org.lpoo.g55.model.elements.items.products;

import org.lpoo.g55.model.utils.Position;

public class Milk extends Product {

    private final static String DESCRIPTION = "A jug of cow's milk.";
    private final static int PRICE = 125;
    private final static int SELLING_PRICE = 70;

    public Milk(int quantity, Position position) {
        super(quantity, position);
    }

    @Override
    public String getDescription() {
        return Milk.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Milk.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Milk.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Milk";
    }
}
