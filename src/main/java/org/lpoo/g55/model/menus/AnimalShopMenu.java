package org.lpoo.g55.model.menus;

import org.lpoo.g55.Game;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.commands.shopping.LeaveShopCommand;
import org.lpoo.g55.commands.shopping.animals.AddAnimalToChartCommand;
import org.lpoo.g55.commands.shopping.animals.RemoveAnimalFromChartCommand;
import org.lpoo.g55.commands.shopping.animals.ShopAnimalCheckoutCommand;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.animals.barn.Cow;
import org.lpoo.g55.model.elements.animals.barn.Pig;
import org.lpoo.g55.model.elements.animals.barn.Sheep;
import org.lpoo.g55.model.elements.animals.coop.Bunny;
import org.lpoo.g55.model.elements.animals.coop.Chicken;
import org.lpoo.g55.model.elements.animals.coop.Dinosaur;
import org.lpoo.g55.model.market.shops.Shop;
import org.lpoo.g55.model.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimalShopMenu extends ShopMenu {

    private final static String BACKGROUND = "#000000";
    private final static String FOREGROUND = "#FFFFFF";

    private Map<Animal, Integer> chart;

    public AnimalShopMenu(Game game, Farm farm, Shop shop, Position topLeftCorner) {
        super(game, farm, shop, topLeftCorner);

        this.initShoppingChart();
        this.initEntries();
    }

    @Override
    protected void initShoppingChart() {
        this.chart = new HashMap<>();

        Position dummyPosition = new Position(35, 17);

        // barn animals empty slots
        this.chart.put(new Cow(dummyPosition), 0);
        this.chart.put(new Pig(dummyPosition), 0);
        this.chart.put(new Sheep(dummyPosition), 0);

        // coop animals empty slots
        this.chart.put(new Bunny(dummyPosition), 0);
        this.chart.put(new Chicken(dummyPosition), 0);
        this.chart.put(new Dinosaur(dummyPosition), 0);
    }
    @Override
    protected void initEntries() {
        this.entries = new ArrayList<>();

        for (Animal animal : this.chart.keySet()) {
            this.entries.add(new AddAnimalToChartCommand(animal, this.chart));
            this.entries.add(new RemoveAnimalFromChartCommand(animal, this.chart));
        }

        this.entries.add(new ShopAnimalCheckoutCommand(game, farm, this.chart));
        this.entries.add(new LeaveShopCommand(game, farm));
    }

    public Map<Animal, Integer> getChart() {
        return this.chart;
    }

    @Override
    public void nextEntry() {
        if (selectedEntry == entries.size() - 2) selectedEntry += 1;
        else selectedEntry += 2;
        selectedEntry = Math.min(selectedEntry, entries.size() - 1);
    }
    @Override
    public void previousEntry() {
        if (selectedEntry == entries.size() - 1) selectedEntry -= 1;
        else selectedEntry -= 2;
        selectedEntry = Math.max(selectedEntry, 0);
    }

    @Override
    public String getBackground() {
        return AnimalShopMenu.BACKGROUND;
    }
    @Override
    public String getForeground() {
        return AnimalShopMenu.FOREGROUND;
    }
}
