package org.lpoo.g55.commands.farming;

import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.Farm;

public final class HarvestCommand implements Command {

    private final static String TEXT = "Harvest";

    private final Farm FARM;

    public HarvestCommand(Farm farm) {
        this.FARM = farm;
    }

    @Override
    public String getText() {
        return HarvestCommand.TEXT;
    }

    @Override
    public void execute() {
        this.FARM.harvest(this.FARM.getCrop(this.FARM.getPlayer().getPosition()));
    }
}
