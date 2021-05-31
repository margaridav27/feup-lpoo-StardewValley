package org.lpoo.g55.model.buildings;

import org.lpoo.g55.model.elements.animals.barn.BarnAnimal;
import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.animals.barn.Pig;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

import java.util.ArrayList;
import java.util.List;

public class Barn extends Building {
    private List<BarnAnimal> animals;

    public Barn(Size size, Position entrance, Position exit, int capacity) {
        super(size, entrance, exit, capacity);
        this.animals = new ArrayList<>();
        initializeAnimals();
    }

    public void initializeAnimals() {
        Position initialPos = new Position(5 , 5);
        this.animals.add(new Cow(initialPos));
        this.animals.add(new Pig(initialPos));
    }

    @Override
    public List<BarnAnimal> getAnimals() {
        return this.animals;
    }
    public void setAnimals(List<BarnAnimal> animals) {
        this.animals = animals;
    }

    public BarnAnimal getAnimal(Position position) {
        for (BarnAnimal animal : animals) {
            if (animal.getPosition().equals(position))
                return animal;
        }
        return null;
    }
    public boolean isAnimal(Position position) {
        return getAnimal(position) != null;
    }

    public void addAnimal(BarnAnimal newAnimal, int quantity) {
        for (int i = 0; i < quantity; i++)
            this.animals.add((BarnAnimal) newAnimal.clone());
    }

    public boolean hasCapacity(int quantity) {
        return (animals.size() + quantity) <= capacity;
    }
}
