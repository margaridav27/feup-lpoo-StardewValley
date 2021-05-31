package org.lpoo.g55.model.buildings;

import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.animals.barn.Pig;
import org.lpoo.g55.model.elements.animals.coop.Bunny;
import org.lpoo.g55.model.elements.animals.coop.CoopAnimal;
import org.lpoo.g55.model.elements.animals.coop.Dinosaur;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

import java.util.ArrayList;
import java.util.List;

public class Coop extends Building {
    private List<CoopAnimal> animals;

    public Coop(Size size, Position entrance, Position exit, int capacity) {
        super(size, entrance, exit, capacity);
        this.animals = new ArrayList<>();
        initializeAnimals();
    }

    public void initializeAnimals() {
        Position initialPos = new Position(5 , 5);
        this.animals.add(new Dinosaur(initialPos));
        this.animals.add(new Bunny(initialPos));
    }

    @Override
    public List<CoopAnimal> getAnimals() {
        return this.animals;
    }
    public void setAnimals(List<CoopAnimal> animals) {
        this.animals = animals;
    }

    public CoopAnimal getAnimal(Position position) {
        for (CoopAnimal animal : animals) {
            if (animal.getPosition().equals(position))
                return animal;
        }
        return null;
    }
    public boolean isAnimal(Position position) {
        return getAnimal(position) != null;
    }

    public void addAnimal(CoopAnimal newAnimal, int quantity) {
        for (int i = 0; i < quantity; i++)
            this.animals.add((CoopAnimal) newAnimal.clone());
    }

    public boolean hasCapacity(int quantity) {
        return (animals.size() + quantity) <= capacity;
    }
}
