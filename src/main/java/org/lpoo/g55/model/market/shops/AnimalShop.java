package org.lpoo.g55.model.market.shops;

import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.animals.barn.Pig;
import org.lpoo.g55.model.elements.animals.barn.Sheep;
import org.lpoo.g55.model.elements.animals.coop.Bunny;
import org.lpoo.g55.model.elements.animals.coop.Chicken;
import org.lpoo.g55.model.elements.animals.coop.Dinosaur;

import java.util.HashMap;
import java.util.Map;

public class AnimalShop extends Shop {

    public AnimalShop(Market MARKET, String NAME, Position POSITION) {
        super(MARKET, NAME, POSITION);
    }
}
