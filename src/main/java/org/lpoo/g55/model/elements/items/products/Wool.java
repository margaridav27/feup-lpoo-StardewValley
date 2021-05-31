package org.lpoo.g55.model.elements.items.products;

import org.lpoo.g55.model.utils.Position;

public class Wool extends Product {

    private final static String DESCRIPTION = "Soft, fluffy wool.";
    private final static int PRICE = 340;
    private final static int SELLING_PRICE = 220;

    public Wool(int quantity, Position position) {
        super(quantity, position);
    }

    @Override
    public String getDescription() {
        return Wool.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Wool.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Wool.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Wool";
    }
}
