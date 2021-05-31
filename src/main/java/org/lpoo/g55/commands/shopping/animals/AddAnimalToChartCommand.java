package org.lpoo.g55.commands.shopping.animals;

import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.elements.animals.Animal;

import java.util.Map;

public final class AddAnimalToChartCommand implements Command {
    private final String TEXT;

    private final Animal ANIMAL;
    private Map<Animal, Integer> chart;

    public AddAnimalToChartCommand(Animal ANIMAL, Map<Animal, Integer> chart) {
        this.ANIMAL = ANIMAL;
        this.chart = chart;
        this.TEXT = this.ANIMAL.toString();
    }

    @Override
    public String getText() {
        return this.TEXT;
    }

    @Override
    public void execute() {
        this.chart.merge(this.ANIMAL, 1, Integer::sum);
    }
}
