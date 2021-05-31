package org.lpoo.g55.observers;

import org.lpoo.g55.model.elements.items.crops.Crop;

public class CropObserver {
    private final Crop observableCrop;
    private long initialTime;

    public CropObserver(Crop observableCrop) {
        this.observableCrop = observableCrop;
        this.initialTime = System.currentTimeMillis();
    }

    public Crop getObservableCrop() {
        return this.observableCrop;
    }

    public void wasWatered() {
        this.initialTime = System.currentTimeMillis(); // reset time counting
    }

    public void update() {
        if (System.currentTimeMillis() - initialTime >= Crop.getGrowthDuration()) {
            int nextStateOrdinal = Math.min(
                    Crop.CropState.values().length - 2,
                    this.observableCrop.getState().ordinal() + 1
            );
            Crop.CropState nextState = Crop.CropState.values()[nextStateOrdinal];
            this.observableCrop.setState(nextState);
            this.initialTime = System.currentTimeMillis(); // reset time counting
        }
    }
}
