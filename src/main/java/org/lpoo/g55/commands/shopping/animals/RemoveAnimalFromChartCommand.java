package org.lpoo.g55.commands.shopping.animals;

import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.elements.animals.Animal;

import java.util.Map;

public final class RemoveAnimalFromChartCommand implements Command {
    private final String TEXT;

    private final Animal ANIMAL;
    private Map<Animal, Integer> chart;

    public RemoveAnimalFromChartCommand(Animal ANIMAL, Map<Animal, Integer> chart) {
        this.ANIMAL = ANIMAL;
        this.chart = chart;
        this.TEXT = this.ANIMAL.toString();
    }

    @Override
    public void execute() {
        this.chart.merge(this.ANIMAL, -1, Integer::sum);
        if (this.chart.get(this.ANIMAL) < 0)
            this.chart.put(this.ANIMAL, 0);
    }

    @Override
    public String getText() {
        return this.TEXT;
    }
}
