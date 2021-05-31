package org.lpoo.g55.model.elements.animals.coop;

import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.items.products.DinosaurEgg;
import org.lpoo.g55.model.elements.items.products.Product;
import org.lpoo.g55.model.utils.Position;

import java.util.Random;

public final class Dinosaur extends CoopAnimal {

    private final static int PRICE = 30000;

    public Dinosaur(Position position) {
        super(position);
    }

    @Override
    public Product produce() {
        this.caringFactor = 0;
        this.isReadyToProduce = false;

        return new DinosaurEgg(this.getProductionLoad(), this.position);
    }

    @Override
    public int getPrice() {
        return Dinosaur.PRICE;
    }

    @Override
    public String toString() {
        return "Dinosaur";
    }

    @Override
    public int hashCode() {
        return 37 * position.getX() * position.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dinosaur that = (Dinosaur) o;
        return position.equals(that.position);
    }

    @Override
    public Object clone() {
        return new Dinosaur(this.getPosition());
    }
}
