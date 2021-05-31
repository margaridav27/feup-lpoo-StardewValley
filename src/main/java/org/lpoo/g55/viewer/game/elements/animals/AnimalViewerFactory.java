package org.lpoo.g55.viewer.game.elements.animals;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.animals.barn.Pig;
import org.lpoo.g55.model.elements.animals.barn.Sheep;
import org.lpoo.g55.model.elements.animals.coop.Bunny;
import org.lpoo.g55.model.elements.animals.coop.Chicken;
import org.lpoo.g55.model.elements.animals.coop.Dinosaur;
import org.lpoo.g55.viewer.game.elements.animals.barn.CowViewer;
import org.lpoo.g55.viewer.game.elements.animals.barn.PigViewer;
import org.lpoo.g55.viewer.game.elements.animals.barn.SheepViewer;
import org.lpoo.g55.viewer.game.elements.animals.coop.BunnyViewer;
import org.lpoo.g55.viewer.game.elements.animals.coop.ChickenViewer;
import org.lpoo.g55.viewer.game.elements.animals.coop.DinosaurViewer;

import java.io.IOException;

public class AnimalViewerFactory {
    public void draw(Animal animal, GUI gui) throws IOException {
        if (animal instanceof Bunny) new BunnyViewer().draw((Bunny) animal, gui);
        if (animal instanceof Chicken) new ChickenViewer().draw((Chicken) animal, gui);
        if (animal instanceof Cow) new CowViewer().draw((Cow) animal, gui);
        if (animal instanceof Dinosaur) new DinosaurViewer().draw((Dinosaur) animal, gui);
        if (animal instanceof Pig) new PigViewer().draw((Pig) animal, gui);
        if (animal instanceof Sheep) new SheepViewer().draw((Sheep) animal, gui);
    }
}
