package org.lpoo.g55.viewer.game.buildings;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.elements.animals.coop.CoopAnimal;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.game.elements.PlayerViewer;
import org.lpoo.g55.viewer.game.elements.animals.AnimalViewerFactory;

import java.io.IOException;
import java.util.List;

public class CoopViewer extends Viewer<Coop> implements BuildingViewer<Coop> {
    public CoopViewer(Coop model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clearGUI();


        gui.drawCoop(getModel());
        drawPlayer(gui, getModel().getPlayer(), new PlayerViewer());
        drawAnimals(gui, getModel().getAnimals());

        gui.refreshGUI();
    }

    @Override
    public void drawOutside(GUI gui) {
        gui.drawCoopOutside(getModel().getEntrancePosition());
    }

    private void drawPlayer(GUI gui, Player player, PlayerViewer viewer) throws IOException {
        viewer.draw(player, gui);
    }

    private void drawAnimals(GUI gui, List<CoopAnimal> animals) throws IOException {
        for (CoopAnimal animal : animals)
            new AnimalViewerFactory().draw(animal, gui);
    }
}
