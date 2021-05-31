package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.model.farm.Farm;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    protected final Game game;
    protected final Farm farm;

    protected Position topLeftCorner;
    protected Size size;

    protected List<Command> entries = new ArrayList<>();
    protected int selectedEntry = 0;

    public Menu(Game game, Farm farm, Position topLeftCorner) {
        this.game = game;
        this.farm = farm;
        this.topLeftCorner = topLeftCorner;
    }

    public Game getGame() {
        return this.game;
    }

    public Farm getFarm() {
        return this.farm;
    }

    public List<Command> getEntries() {
        return entries;
    }

    public int getSelectedEntry() {
        return selectedEntry;
    }

    public void nextEntry() {
        selectedEntry++;
        selectedEntry = Math.min(selectedEntry, entries.size() - 1);
    }

    public void previousEntry() {
        selectedEntry--;
        selectedEntry = Math.max(selectedEntry, 0);
    }

    public Position getPosition() {
        return topLeftCorner;
    }

    public Size getSize() {
        return size;
    }

    public abstract String getTitle();

    public abstract String getBackground();

    public abstract String getForeground();
}
