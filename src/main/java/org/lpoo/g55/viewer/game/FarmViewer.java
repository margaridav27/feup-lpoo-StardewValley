package org.lpoo.g55.viewer.game;

import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Building;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.elements.items.crops.Crop;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.viewer.Viewer;
import org.lpoo.g55.viewer.game.buildings.BarnViewer;
import org.lpoo.g55.viewer.game.buildings.BuildingViewer;
import org.lpoo.g55.viewer.game.buildings.CoopViewer;
import org.lpoo.g55.viewer.game.elements.PlayerViewer;
import org.lpoo.g55.viewer.game.elements.items.crops.CropViewerFactory;

import java.io.IOException;
import java.util.List;

public class FarmViewer extends Viewer<Farm> {

    public FarmViewer(Farm farm) {
        super(farm);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clearGUI();

        gui.drawFarm(getModel());
        
        drawBuilding(gui, new CoopViewer(getModel().getCoop()));
        drawBuilding(gui, new BarnViewer(getModel().getBarn()));
        drawYard(gui, new FieldViewer(getModel().getYard()));

        drawPlayer(gui, getModel().getPlayer(), new PlayerViewer());

        // TODO IMPROVE this
        gui.drawElement(getModel().getMarket().getEntrancePosition(), 'M', "#FFFFFF", null);

        drawCrops(gui, getModel().getCrops());

        gui.refreshGUI();
    }

    private <T extends Building> void drawBuilding(GUI gui, BuildingViewer<T> viewer) throws IOException {
        viewer.drawOutside(gui);
    }

    private void drawYard(GUI gui, FieldViewer viewer) throws IOException {
        viewer.draw(gui);
    }

    private void drawPlayer(GUI gui, Player player, PlayerViewer viewer) throws IOException {
        viewer.draw(player, gui);
    }

    private void drawCrops(GUI gui, List<Crop> crops) throws IOException {
        for (Crop crop : crops)
            new CropViewerFactory().draw(crop, gui);
    }
}
