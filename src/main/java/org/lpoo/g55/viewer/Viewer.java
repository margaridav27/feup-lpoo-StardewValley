package org.lpoo.g55.viewer;

import org.lpoo.g55.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return this.model;
    }

    public abstract void draw(GUI gui) throws IOException;
}

