package org.lpoo.g55.model.elements.items.crops;

import org.lpoo.g55.model.utils.Position;

public final class Wheat extends Crop {
    private final static String DESCRIPTION = "Le pain et la baguette.";
    private final static int PRICE = 5;
    private final static int SELLING_PRICE = 25;

    public Wheat(Position position, CropState initialState) {
        super(position, initialState);
    }

    @Override
    public final String getDescription() {
        return Wheat.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Wheat.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Wheat.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Wheat";
    }

    @Override
    public final int hashCode() {
        return DESCRIPTION.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheat that = (Wheat) o;
        return position.equals(that.position) && hashCode() == that.hashCode();
    }

    @Override
    protected Object clone() {
        return new Wheat(this.getPosition(), this.getState());
    }
}
