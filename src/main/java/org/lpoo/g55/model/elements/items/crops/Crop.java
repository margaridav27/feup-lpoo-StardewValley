package org.lpoo.g55.model.elements.items.crops;

import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.elements.items.Item;

public abstract class Crop extends Item {
    public enum CropState {SEED, GROWING, READY, DEAD, HARVESTED}

    private CropState state;

    private final static long GROWTH_DURATION = 5000; // 5s

    protected Crop(Position position, CropState initialState) {
        super(position);
        this.state = initialState;
    }

    public CropState getState() {
        return this.state;
    }

    public void setState(CropState state) {
        this.state = state;
    }

    public static long getGrowthDuration() {
        return Crop.GROWTH_DURATION;
    }

    @Override
    public abstract String toString();

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    protected abstract Object clone() throws CloneNotSupportedException;
}
