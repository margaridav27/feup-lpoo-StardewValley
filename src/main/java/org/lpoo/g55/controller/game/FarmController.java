package org.lpoo.g55.controller.game;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.farming.HarvestCommand;
import org.lpoo.g55.commands.farming.WaterCommand;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.controller.game.elements.PlayerController;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.menus.InventoryMenu;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.menus.CropMenu;
import org.lpoo.g55.observers.CropObserver;
import org.lpoo.g55.states.menus.CropMenuState;
import org.lpoo.g55.states.menus.InventoryMenuState;

public class FarmController extends Controller<Farm> {
    private final PlayerController<Farm> PLAYER_CONTROLLER;

    public FarmController(Farm farm) {
        super(farm);
        this.PLAYER_CONTROLLER = new PlayerController<>(farm);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case QUIT:
                game.setState(null);
                break;
            case CHAR_C:
                if (getModel().canCrop(getModel().getPlayer().getPosition()))
                    game.setState(new CropMenuState(new CropMenu(game, getModel(), new Position(18, 14))));
                break;
            case CHAR_W:
                if (getModel().canWater(getModel().getPlayer().getPosition()))
                    new WaterCommand(getModel()).execute();
                break;
            case CHAR_H:
                if (getModel().canHarvest(getModel().getPlayer().getPosition()))
                    new HarvestCommand(getModel()).execute();
                break;
            case CHAR_I:
                game.setState(new InventoryMenuState(new InventoryMenu(game, getModel(), new Position(0, 0))));
                break;
            default:
                PLAYER_CONTROLLER.step(game, action, time);
                break;
        }

        for (CropObserver observer : getModel().getCropObservers())
            observer.update();
    }
}
