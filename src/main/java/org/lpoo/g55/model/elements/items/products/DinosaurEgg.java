package org.lpoo.g55.model.elements.items.products;

import org.lpoo.g55.model.utils.Position;

public class DinosaurEgg extends Product {

    private final static String DESCRIPTION = "A giant dino egg... The entire shell is still intact!";
    private final static int PRICE = 450;
    private final static int SELLING_PRICE = 350;

    public DinosaurEgg(int quantity, Position position) {
        super(quantity, position);
    }

    @Override
    public String getDescription() {
        return DinosaurEgg.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return DinosaurEgg.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return DinosaurEgg.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "DinosaurEgg";
    }
}
