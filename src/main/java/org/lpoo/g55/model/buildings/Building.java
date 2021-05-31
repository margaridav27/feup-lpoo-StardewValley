package org.lpoo.g55.model.buildings;

import org.lpoo.g55.controller.game.elements.PlayerControllerInteraction;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

import java.util.List;

public abstract class Building implements PlayerControllerInteraction {
    // Building must know in which farm it is in order to know where to come back
    protected Farm farm;
    protected Player player;

    protected final Size size;
    protected final Position entrancePosition;
    protected final Position exitPosition;

    protected int capacity;

    public Building(Size size, Position entrancePosition, Position exitPosition, int capacity) {
        this.size = size;
        this.entrancePosition = entrancePosition;
        this.exitPosition = exitPosition;
        this.capacity = Math.max(15, capacity);
    }

    public Size getSize() {
        return size;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Farm getFarm() {
        return farm;
    }
    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Position getEntrancePosition() {
        return entrancePosition;
    }
    public Position getExitPosition() {
        return exitPosition;
    }

    private static boolean between(int min, int target, int max) {
        return min <= target && target <= max;
    }
    private boolean canMove(Position position) {
        return between(4, position.getX(), size.getWidth() + 1) &&
               between(5, position.getY(), size.getHeight() + 2);
    }

    @Override
    public boolean canPlayerMove(Position playerCurrentPosition) {
        return canMove(playerCurrentPosition);
    }

    public boolean canAnimalMove(Position animalCurrentPosition) {
        if (animalCurrentPosition.equals(exitPosition))
            return false;
        return canMove(animalCurrentPosition);
    }

    public abstract List<? extends Animal> getAnimals();
}
