package org.lpoo.g55.commands.farming;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.BackToFarmCommand;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.elements.items.crops.Crop;

public final class CropCommand implements Command {

    private final String TEXT;

    private final Game GAME;
    private final Farm FARM;
    private final Crop CROP;

    public CropCommand(Game game, Farm farm, Crop crop) {
        this.GAME = game;
        this.FARM = farm;
        this.CROP = crop;
        this.TEXT = this.CROP.toString();
    }

    @Override
    public String getText() {
        return this.TEXT;
    }

    @Override
    public void execute() {
        this.CROP.setPosition(this.FARM.getPlayer().getPosition());
        this.FARM.crop(this.CROP);
        this.FARM.getPlayer().removeItemFromInventory(this.CROP, 1);
        new BackToFarmCommand(this.GAME, this.FARM).execute();
    }
}
