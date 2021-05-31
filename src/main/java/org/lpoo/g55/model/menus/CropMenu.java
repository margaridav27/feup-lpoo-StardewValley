package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.gaming.BackToFarmCommand;
import org.lpoo.g55.commands.farming.CropCommand;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.elements.items.crops.*;

import java.util.Arrays;
import java.util.Map;

public class CropMenu extends Menu {

    private final static String TITLE = "Crop Menu";

    private final static String BACKGROUND = "#000000";
    private final static String FOREGROUND = "#FFFFFF";

    public CropMenu(Game game, Farm farm, Position topLeftCorner) {
        super(game, farm, topLeftCorner);
        this.size = new Size(12, 7);

        for (Item item : farm.getPlayer().getInventory().keySet()) {
            if (item instanceof Pumpkin)
                this.entries.add(new CropCommand(game, farm, new Pumpkin(farm.getPlayer().getPosition(), Crop.CropState.SEED)));
            else if (item instanceof Potato)
                this.entries.add(new CropCommand(game, farm, new Potato(farm.getPlayer().getPosition(), Crop.CropState.SEED)));
            else if (item instanceof Cauliflower)
                this.entries.add(new CropCommand(game, farm, new Cauliflower(farm.getPlayer().getPosition(), Crop.CropState.SEED)));
            else if (item instanceof Wheat)
                this.entries.add(new CropCommand(game, farm, new Wheat(farm.getPlayer().getPosition(), Crop.CropState.SEED)));
        }
        this.entries.add(new BackToFarmCommand(game, farm));
    }

    @Override
    public String getTitle() {
        return CropMenu.TITLE;
    }
    @Override
    public String getBackground() {
        return CropMenu.BACKGROUND;
    }
    @Override
    public String getForeground() {
        return CropMenu.FOREGROUND;
    }
}
