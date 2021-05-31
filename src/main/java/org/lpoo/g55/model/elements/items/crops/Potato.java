package org.lpoo.g55.model.elements.items.crops;


import org.lpoo.g55.model.utils.Position;

public final class Potato extends Crop {
    private final static String DESCRIPTION = "Well, it's a potato...";
    private final static int PRICE = 25;
    private final static int SELLING_PRICE = 80;

    public Potato(Position position, CropState initialState) {
        super(position, initialState);
    }

    @Override
    public final String getDescription() {
        return Potato.DESCRIPTION;
    }

    @Override
    public int getPrice() {
        return Potato.PRICE;
    }

    @Override
    public int getSellingPrice() {
        return Potato.SELLING_PRICE;
    }

    @Override
    public String toString() {
        return "Potato";
    }

    @Override
    public final int hashCode() {
        return DESCRIPTION.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Potato that = (Potato) o;
        return position.equals(that.position) && hashCode() == that.hashCode();
    }

    @Override
    protected Object clone() {
        return new Potato(this.getPosition(), this.getState());
    }
}
