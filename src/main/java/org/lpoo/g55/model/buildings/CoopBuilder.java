package org.lpoo.g55.model.buildings;

import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

public class CoopBuilder {
    public Coop buildCoop(Farm farm) {
        Position coopEntrance = new Position(45, 16);
        Position coopExit = new Position(27, 30);
        Coop coop = new Coop(Size.DEFAULT_WORLD_SIZE, coopEntrance, coopExit, 15);

        coop.setPlayer(farm.getPlayer());
        coop.setFarm(farm);
        return coop;
    }
}
