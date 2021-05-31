package org.lpoo.g55.model.buildings;

import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

public class BarnBuilder {
    public Barn buildBarn(Farm farm) {
        Position barnEntrance = new Position(13, 13);
        Position barnExit = new Position(27, 30);
        Barn barn = new Barn(Size.DEFAULT_WORLD_SIZE, barnEntrance, barnExit, 15);

        barn.setPlayer(farm.getPlayer());
        barn.setFarm(farm);
        return barn;
    }
}
