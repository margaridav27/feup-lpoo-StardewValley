package org.lpoo.g55.model.elements.items.products;

import org.lpoo.g55.model.utils.Position;

public class Egg extends Product {

    private final static String DESCRIPTION = "A regular white chicken egg.";
    private final static int PRICE = 40;
    private final static int SELLING_PRICE = 30;

    public Egg(int quantity, Position position) {
        super(quantity, position);
    }

    @Override
    public String getDescription() {
        return Egg.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Egg.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Egg.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Egg";
    }
}
