package org.lpoo.g55.model.farm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.model.elements.items.crops.Cauliflower;
import org.lpoo.g55.model.elements.items.crops.Crop;
import org.lpoo.g55.model.elements.items.crops.Pumpkin;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.mockito.Mockito;

public class FarmTest {
    private Farm farm;

    @BeforeEach
    void setUp() {
        farm = new FarmBuilder().buildFarm();
    }

    @Test
    void addCropTest() {
        Crop plantedCrop = Mockito.mock(Crop.class);
        farm.getPlayer().setPosition(new Position(20, 25));
        farm.crop(plantedCrop);

        Assertions.assertEquals(1, farm.getCrops().size());
        Assertions.assertTrue(farm.getCrops().contains(plantedCrop));
    }

    @Test
    void canCropTest() {
        Crop plantedCrop = Mockito.mock(Crop.class);
        farm.getPlayer().setPosition(new Position(20, 25));

        farm.crop(plantedCrop);
        Mockito.when(plantedCrop.getPosition()).thenReturn(new Position(20, 25));

        farm.getPlayer().setPosition(new Position(21, 25));

        Crop cropToPlant = Mockito.mock(Crop.class);
        Mockito.when(cropToPlant.getPosition()).thenReturn(farm.getPlayer().getPosition());
        Assertions.assertFalse(farm.canCrop(cropToPlant.getPosition()));
    }

    @Test
    void canPlayerMoveTest() {
        Size farmSize = farm.getSize();
        Position testPosition = new Position(farmSize.getWidth() + 1, farmSize.getHeight() + 3);
        Assertions.assertFalse(farm.canPlayerMove(testPosition));

        testPosition = new Position(0, 0);
        Assertions.assertFalse(farm.canPlayerMove(testPosition));

        testPosition = farm.getYard().getTopLeftCorner();
        Assertions.assertFalse(farm.canPlayerMove(testPosition));

        testPosition = new Position(5, 5);
        Assertions.assertTrue(farm.canPlayerMove(testPosition));
    }

    @Test
    void canHarvestTest() {
        Position testPosition = new Position(10, 10);
        Crop plantedCrop = new Pumpkin(testPosition, Crop.CropState.SEED);

        farm.crop(plantedCrop);

        Assertions.assertFalse(farm.canHarvest(new Position(10, 11)));
        Assertions.assertFalse(farm.canHarvest(testPosition));

        plantedCrop.setState(Crop.CropState.READY);
        Assertions.assertTrue(farm.canHarvest(testPosition));
    }

    @Test
    void harvestTest() {
        Farm farm = new FarmBuilder().buildFarm();

        Crop plantedCrop =  new Cauliflower(new Position(10, 10), Crop.CropState.GROWING);
        farm.crop(plantedCrop);

        Assertions.assertEquals(1, farm.getCropObservers().size());
        Assertions.assertEquals(1, farm.getCrops().size());
        Assertions.assertEquals(Crop.CropState.GROWING, plantedCrop.getState());

        plantedCrop.setState(Crop.CropState.READY);
        farm.harvest(plantedCrop);

        Assertions.assertEquals(0, farm.getCropObservers().size());
        Assertions.assertEquals(0, farm.getCrops().size());
        Assertions.assertEquals(Crop.CropState.HARVESTED, plantedCrop.getState());
    }
}
