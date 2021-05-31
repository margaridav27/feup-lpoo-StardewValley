package org.lpoo.g55.model.elements.animals;

import org.lpoo.g55.model.elements.Element;
import org.lpoo.g55.model.elements.items.products.Product;
import org.lpoo.g55.model.utils.Position;

import java.util.Random;

public abstract class Animal extends Element {
    private final static int CARING_FACTOR_INCREMENT = 25;
    private final static int PRODUCING_THRESHOLD = 75;

    protected int caringFactor;
    protected boolean isReadyToProduce;

    public Animal(Position position) {
        super(position);
        this.caringFactor = 0;
        this.isReadyToProduce = false;
    }

    public abstract int getPrice();

    public int getCaringFactor() {
        return this.caringFactor;
    }
    public void care() {
        this.caringFactor += CARING_FACTOR_INCREMENT;
        if (caringFactor >= PRODUCING_THRESHOLD) {
            isReadyToProduce = true;
        }
    }

    public boolean isReadyToProduce() {
        return this.isReadyToProduce;
    }
    protected int getProductionLoad() {
        int productionLoad = new Random().nextInt(10) + 1;
        int luckFactor = new Random().nextInt(Math.max(1, (this.caringFactor / 100)));

        return (productionLoad + luckFactor);
    }
    public abstract Product produce();

    @Override
    public abstract String toString();

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract Object clone();
}
