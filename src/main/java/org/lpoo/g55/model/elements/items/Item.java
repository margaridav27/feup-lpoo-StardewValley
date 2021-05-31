package org.lpoo.g55.model.elements.items;

import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.elements.Element;

public abstract class Item extends Element {
    public Item(Position position) {
        super(position);
    }

    public abstract String getDescription();

    public abstract int getPrice();

    public abstract int getSellingPrice();

    @Override
    public abstract String toString();
}
