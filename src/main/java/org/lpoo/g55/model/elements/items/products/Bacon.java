package org.lpoo.g55.model.elements.items.products;

import org.lpoo.g55.model.utils.Position;

public class Bacon extends Product {

    private final static String DESCRIPTION = "Perfect for a late morning breakfast.";
    private final static int PRICE = 40;
    private final static int SELLING_PRICE = 175;

    public Bacon(int quantity, Position position) {
        super(quantity, position);
    }

    @Override
    public String getDescription() {
        return Bacon.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Bacon.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Bacon.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Bacon";
    }
}
