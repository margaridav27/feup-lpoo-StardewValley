package org.lpoo.g55.model.farm;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

public class FieldTest {

    private Field field;

    @BeforeEach
    void setUp() {
        Position fieldPosition = new Position(0, 0);
        Position gatePosition = new Position(20, 0);

        field = new Field(Size.DEFAULT_WORLD_SIZE, fieldPosition, gatePosition);
    }

    @Property
    public void testFencePositionX(@ForAll @IntRange(min = 1, max = 50) int x) {
        setUp();
        int yBottom = field.getTopLeftCorner().getY() + field.getSize().getHeight() - 1;
        Position fencePosition = new Position((x - 1) + field.getTopLeftCorner().getX(), yBottom);
        Assertions.assertTrue(field.isFence(fencePosition));
    }

    @Property
    public void testFencePositionY(@ForAll @IntRange(min = 1, max = 30) int y) {
        setUp();
        int xLeft = field.getTopLeftCorner().getX();
        Position fencePositionLeft = new Position(xLeft, y - 1);
        Assertions.assertTrue(field.isFence(fencePositionLeft));

        int xRight = xLeft + field.getSize().getWidth() - 1;
        Position fencePositionRight = new Position(xRight, y - 1);
        Assertions.assertTrue(field.isFence(fencePositionRight));
    }

    @Property
    public void testGatePosition(@ForAll @IntRange(max = 4) int gateDelta) {
        setUp();
        Position gatePosition = new Position(gateDelta + field.getGate().getX(), field.getGate().getY());
        Assertions.assertTrue(field.isGate(gatePosition));
    }

}
