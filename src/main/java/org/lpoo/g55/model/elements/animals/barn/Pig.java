package org.lpoo.g55.model.elements.animals.barn;

import org.lpoo.g55.model.elements.animals.coop.Dinosaur;
import org.lpoo.g55.model.elements.items.products.Bacon;
import org.lpoo.g55.model.elements.items.products.Product;
import org.lpoo.g55.model.utils.Position;

public final class Pig extends BarnAnimal {

    private final static int PRICE = 16000;

    public Pig(Position position) {
        super(position);
    }

    @Override
    public Product produce() {
        this.caringFactor = 0;
        this.isReadyToProduce = false;

        return new Bacon(this.getProductionLoad(), this.position);
    }

    @Override
    public int getPrice() {
        return Pig.PRICE;
    }

    @Override
    public String toString() {
        return "Pig";
    }

    @Override
    public int hashCode() {
        return 37 * position.getX() * position.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pig that = (Pig) o;
        return position.equals(that.position);
    }

    @Override
    public Object clone() {
        return new Pig(this.getPosition());
    }
}
