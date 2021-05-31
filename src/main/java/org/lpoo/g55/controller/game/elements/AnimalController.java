package org.lpoo.g55.controller.game.elements;

import org.lpoo.g55.Game;
import org.lpoo.g55.controller.Controller;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Building;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.utils.Position;

import java.util.List;
import java.util.Random;

public class AnimalController<T extends Building> extends Controller<T> {
    List<? extends Animal> animals;

    public AnimalController(T building, List<? extends Animal> animals) {
        super(building);
        this.animals = animals;
    }

    public void moveAnimalLeft(Animal animal) {
        moveAnimal(animal, animal.getPosition().getLeft());
    }
    public void moveAnimalRight(Animal animal) {
        moveAnimal(animal, animal.getPosition().getRight());
    }
    public void moveAnimalDown(Animal animal) {
        moveAnimal(animal, animal.getPosition().getDown());
    }
    public void moveAnimalUp(Animal animal) {
        moveAnimal(animal, animal.getPosition().getUp());
    }
    private void moveAnimal(Animal animal, Position position) {
        if (getModel().canAnimalMove(position))
            animal.setPosition(position);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        Random directionGenerator = new Random();

        for (Animal animal : animals) {
            switch (directionGenerator.nextInt(20)) {
                case 0:
                    moveAnimalLeft(animal);
                    break;
                case 1:
                    moveAnimalRight(animal);
                    break;
                case 2:
                    moveAnimalDown(animal);
                    break;
                case 3:
                    moveAnimalUp(animal);
                    break;
                default:
                    break;
            }
        }
    }
}
