package org.lpoo.g55.model.elements.animals.coop;

import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.animals.barn.Pig;
import org.lpoo.g55.model.elements.items.products.Product;
import org.lpoo.g55.model.elements.items.products.Wool;
import org.lpoo.g55.model.utils.Position;

public final class Bunny extends CoopAnimal {

    private final static int PRICE = 8000;

    public Bunny(Position position) {
        super(position);
    }

    @Override
    public Product produce() {
        this.caringFactor = 0;
        this.isReadyToProduce = false;

        return new Wool(this.getProductionLoad(), this.position);
    }

    @Override
    public int getPrice() {
        return Bunny.PRICE;
    }

    @Override
    public String toString() {
        return "Bunny";
    }

    @Override
    public int hashCode() {
        return 37 * position.getX() * position.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bunny that = (Bunny) o;
        return position.equals(that.position);
    }

    @Override
    public Object clone() {
        return new Bunny(this.getPosition());
    }
}
