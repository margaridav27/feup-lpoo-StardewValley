package org.lpoo.g55.model.elements.animals.coop;

import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.items.products.Egg;
import org.lpoo.g55.model.elements.items.products.Product;
import org.lpoo.g55.model.utils.Position;

public final class Chicken extends CoopAnimal {

    private final static int PRICE = 800;

    public Chicken(Position position) {
        super(position);
    }

    @Override
    public Product produce() {
        this.caringFactor = 0;
        this.isReadyToProduce = false;

        return new Egg(this.getProductionLoad(), this.position);
    }

    @Override
    public int getPrice() {
        return Chicken.PRICE;
    }

    @Override
    public String toString() {
        return "Chicken";
    }

    @Override
    public int hashCode() {
        return 37 * position.getX() * position.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chicken that = (Chicken) o;
        return position.equals(that.position);
    }

    @Override
    public Object clone() {
        return new Chicken(this.getPosition());
    }
}
