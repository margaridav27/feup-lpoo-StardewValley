package org.lpoo.g55.model.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.animals.coop.Dinosaur;
import org.lpoo.g55.model.utils.Position;

public class AnimalTest {

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Dinosaur(new Position(10, 10));
    }

    @Test
    void careTest() {
        Assertions.assertEquals(0, animal.getCaringFactor());

        animal.care();
        Assertions.assertEquals(25, animal.getCaringFactor());
        animal.care();
        animal.care();
        Assertions.assertEquals(75, animal.getCaringFactor());
        Assertions.assertTrue(animal.isReadyToProduce());
    }
}
