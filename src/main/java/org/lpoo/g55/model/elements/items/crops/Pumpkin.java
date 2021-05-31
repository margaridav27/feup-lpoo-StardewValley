package org.lpoo.g55.model.elements.items.crops;

import org.lpoo.g55.model.utils.Position;

public final class Pumpkin extends Crop {
    private final static String DESCRIPTION = "Is the Halloween today?";
    private final static int PRICE = 50;
    private final static int SELLING_PRICE = 320;

    public Pumpkin(Position position, CropState initialState) {
        super(position, initialState);
    }

    @Override
    public final String getDescription() {
        return Pumpkin.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Pumpkin.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Pumpkin.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Pumpkin";
    }

    @Override
    public final int hashCode() {
        return DESCRIPTION.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pumpkin that = (Pumpkin) o;
        return position.equals(that.position) && hashCode() == that.hashCode();
    }

    @Override
    protected Object clone() {
        return new Pumpkin(this.getPosition(), this.getState());
    }
}
