package org.lpoo.g55.model.elements.items.crops;

import org.lpoo.g55.model.utils.Position;

public final class Cauliflower extends Crop {
    private final static String DESCRIPTION = "Greenery from the garden";
    private final static int PRICE = 40;
    private final static int SELLING_PRICE = 175;

    public Cauliflower(Position position, CropState initialState) {
        super(position, initialState);
    }

    @Override
    public final String getDescription() {
        return Cauliflower.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Cauliflower.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Cauliflower.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Cauliflower";
    }

    @Override
    public final int hashCode() {
        return DESCRIPTION.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cauliflower that = (Cauliflower) o;
        return position.equals(that.position) && hashCode() == that.hashCode();
    }

    @Override
    protected Object clone() {
        return new Cauliflower(this.getPosition(), this.getState());
    }
}
