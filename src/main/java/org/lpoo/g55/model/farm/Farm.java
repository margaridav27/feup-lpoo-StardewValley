package org.lpoo.g55.model.farm;

import org.lpoo.g55.controller.game.elements.PlayerControllerInteraction;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.elements.items.crops.Crop;
import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.observers.CropObserver;

import java.util.ArrayList;
import java.util.List;

public class Farm implements PlayerControllerInteraction {

    private final Size size;

    private Barn barn;
    private Coop coop;
    private Market market;
    private Field yard;

    private Player player;

    private final List<Crop> crops;
    private final List<CropObserver> cropObservers;

    public Farm(Size size) {
        this.size = size;
        this.crops = new ArrayList<>();
        this.cropObservers = new ArrayList<>();
    }

    public Size getSize() {
        return size;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Barn getBarn() {
        return this.barn;
    }
    public void setBarn(Barn barn) {
        this.barn = barn;
    }

    public Coop getCoop() {
        return this.coop;
    }
    public void setCoop(Coop coop) {
        this.coop = coop;
    }

    public Market getMarket() {
        return market;
    }
    public void setMarket(Market market) {
        this.market = market;
    }

    public Field getYard() {
        return this.yard;
    }
    public void setYard(Field yard) {
        this.yard = yard;
    }

    public List<Crop> getCrops() {
        return this.crops;
    }
    public List<CropObserver> getCropObservers() {
        return this.cropObservers;
    }

    private static boolean between(int min, int target, int max) {
        return min <= target && target <= max;
    }

    @Override
    public boolean canPlayerMove(Position playerCurrentPosition) {
        return between(4, playerCurrentPosition.getX(), size.getWidth() + 1) &&
               between(5, playerCurrentPosition.getY(), size.getHeight() + 2) &&
               !yard.isFence(playerCurrentPosition);
    }

    public Crop getCrop(Position position) {
        for (Crop crop : crops)
            if (crop.getPosition().equals(position)) return crop;
        return null;
    }
    public boolean canCrop(Position position) {
        for (Crop crop : crops) {
            for (int y = - 1; y <= 1; y++) {
                for (int x = - 1; x <= 1; x++) {
                    if (((position.getX() + x) == crop.getPosition().getX()) && ((position.getY() + y) == crop.getPosition().getY())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public void crop(Crop crop) {
        this.crops.add(crop);
        this.cropObservers.add(new CropObserver(crop));
    }

    public boolean canWater(Position position) {
        Crop crop = getCrop(position);
        return crop != null && crop.getState() != Crop.CropState.DEAD;
    }
    public void water(Crop crop) {
        for (CropObserver cropObserver : cropObservers) {
            if (cropObserver.getObservableCrop().equals(crop))
                cropObserver.wasWatered();
        }
    }

    public boolean canHarvest(Position position) {
        Crop crop = getCrop(position);
        return (crop != null && (crop.getState().equals(Crop.CropState.READY) ||
                                 crop.getState().equals(Crop.CropState.DEAD)));
    }
    public void harvest(Crop crop) {
        this.crops.remove(crop);

        if (crop.getState().equals(Crop.CropState.READY)) {
            cropObservers.removeIf(cropObserver -> cropObserver.getObservableCrop().equals(crop));
            crop.setState(Crop.CropState.HARVESTED);
            crop.setPosition(null);
            this.player.addItemToInventory(crop, 1);
        }

        // remove crop observer since it no longer needs to be observed
        else cropObservers.removeIf(cropObserver -> cropObserver.getObservableCrop().equals(crop));
    }
}
