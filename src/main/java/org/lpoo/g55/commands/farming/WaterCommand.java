package org.lpoo.g55.commands.farming;

import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.Farm;

public final class WaterCommand implements Command {

    private final static String TEXT = "Water";

    private final Farm FARM;

    public WaterCommand(Farm FARM) {
        this.FARM = FARM;
    }

    @Override
    public String getText() {
        return WaterCommand.TEXT;
    }

    @Override
    public void execute() {
        this.FARM.water(this.FARM.getCrop(this.FARM.getPlayer().getPosition()));
    }
}
