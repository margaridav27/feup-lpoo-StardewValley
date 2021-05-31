package org.lpoo.g55.model.buildings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

public class BuildingsTest {

    private Building building;

    @BeforeEach
    void setUp() {
        Position entrancePosition = new Position(20, 20);
        Position exitPosition = new Position(10, 10);
        building = new Coop(Size.DEFAULT_WORLD_SIZE, entrancePosition, exitPosition, 15);
    }

    @Test
    void canMoveTest() {
        Assertions.assertTrue(building.canPlayerMove(building.getExitPosition()));
        Assertions.assertFalse(building.canAnimalMove(building.exitPosition));

        Position testPositionFail = new Position(2, 2);
        Position testPositionValid = new Position(20, 20);

        Assertions.assertFalse(building.canPlayerMove(testPositionFail));
        Assertions.assertFalse(building.canAnimalMove(testPositionFail));

        Assertions.assertTrue(building.canPlayerMove(testPositionValid));
        Assertions.assertTrue(building.canAnimalMove(testPositionValid));
    }
}
