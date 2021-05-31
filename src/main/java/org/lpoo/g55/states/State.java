package org.lpoo.g55.states;

import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.Game;
import org.lpoo.g55.viewer.Viewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.controller = this.getController();
        this.viewer = this.getViewer();
    }

    public T getModel() {
        return this.model;
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}
