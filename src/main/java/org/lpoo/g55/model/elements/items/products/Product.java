package org.lpoo.g55.model.elements.items.products;

import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.utils.Position;

public abstract class Product extends Item {

    private final int quantity;

    public Product(int quantity, Position position) {
        super(position);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o != null && getClass() == o.getClass());
    }
}
