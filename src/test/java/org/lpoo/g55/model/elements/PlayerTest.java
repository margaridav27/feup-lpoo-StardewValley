package org.lpoo.g55.model.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.model.elements.items.Item;
import org.mockito.Mockito;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestName", null);
    }

    @Test
    void addItemToInventoryTest() {
        Item item1 = Mockito.mock(Item.class);
        Item item2 = Mockito.mock(Item.class);

        player.addItemToInventory(item1, 1);
        Assertions.assertEquals(1, player.getInventory().get(item1));
        Assertions.assertNull(player.getInventory().get(item2));

        player.addItemToInventory(item1, 1);
        Assertions.assertEquals(2, player.getInventory().get(item1));
        Assertions.assertNull(player.getInventory().get(item2));

        player.addItemToInventory(item1, 2);
        Assertions.assertEquals(4, player.getInventory().get(item1));
        Assertions.assertNull(player.getInventory().get(item2));

        player.addItemToInventory(item1, -2);
        Assertions.assertEquals(4, player.getInventory().get(item1));
        Assertions.assertNull(player.getInventory().get(item2));

        player.addItemToInventory(item2, 1);
        Assertions.assertEquals(4, player.getInventory().get(item1));
        Assertions.assertEquals(1, player.getInventory().get(item2));
    }

    @Test
    void removeItemFromInventoryTest() {
        Item item1 = Mockito.mock(Item.class);
        Item item2 = Mockito.mock(Item.class);

        player.addItemToInventory(item1, 1);
        player.removeItemFromInventory(item1, -1);
        Assertions.assertEquals(1, player.getInventory().get(item1));
        Assertions.assertNull(player.getInventory().get(item2));
        player.removeItemFromInventory(item1, 1);

        player.addItemToInventory(item1, 1);
        player.removeItemFromInventory(item1, 1);
        Assertions.assertNull(player.getInventory().get(item1));
        Assertions.assertNull(player.getInventory().get(item2));

        player.addItemToInventory(item2, 3);
        player.removeItemFromInventory(item2, 0);
        Assertions.assertNull(player.getInventory().get(item1));
        Assertions.assertEquals(3, player.getInventory().get(item2));
    }
}
