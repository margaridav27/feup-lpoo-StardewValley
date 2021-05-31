package org.lpoo.g55.model.market;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

public class MarketTest {

    @Property
    void canMoveTest(@ForAll @IntRange(min = 4, max = 51) int xPosition,
                     @ForAll @IntRange(min = 5, max = 32) int yPosition) {
        Position testPosition = new Position(xPosition, yPosition);
        Market market = new Market(Size.DEFAULT_WORLD_SIZE, null, null);

        Assertions.assertTrue(market.canPlayerMove(testPosition));
    }
}
