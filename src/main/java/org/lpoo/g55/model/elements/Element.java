package org.lpoo.g55.model.elements;

import org.lpoo.g55.model.utils.Position;

public abstract class Element {

    protected Position position;

    public Element(Position position) { this.position = position; }

    public Position getPosition() { return this.position; }
    public void setPosition(Position position) { this.position = position; }
}
