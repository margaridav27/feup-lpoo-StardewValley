package org.lpoo.g55.model.elements.animals.barn;

import org.lpoo.g55.model.elements.items.crops.Potato;
import org.lpoo.g55.model.elements.items.products.Milk;
import org.lpoo.g55.model.elements.items.products.Product;
import org.lpoo.g55.model.utils.Position;

public final class Cow extends BarnAnimal {

    private final static int PRICE = 1500;

    public Cow(Position position) {
        super(position);
    }

    @Override
    public Product produce() {
        this.caringFactor = 0;
        this.isReadyToProduce = false;

        return new Milk(this.getProductionLoad(), this.position);
    }

    @Override
    public int getPrice() {
        return Cow.PRICE;
    }

    @Override
    public String toString() {
        return "Cow";
    }

    @Override
    public int hashCode() {
        return 37 * position.getX() * position.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cow that = (Cow) o;
        return position.equals(that.position);
    }

    @Override
    public Object clone() {
        return new Cow(this.getPosition());
    }
}
