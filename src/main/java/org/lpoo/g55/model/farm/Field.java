package org.lpoo.g55.model.farm;

import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;

public class Field {
    private static final int GATE_SIZE = 4;

    final private Size size;
    final private Position topLeftCorner;
    final private Position gate;

    public Field(Size size, Position topLeftCornerPosition, Position gate) {
        this.size = size;
        this.topLeftCorner = topLeftCornerPosition;
        this.gate = gate;
    }

    public Size getSize() {
        return size;
    }
    public Position getTopLeftCorner() {
        return topLeftCorner;
    }

    public Position getGate() {
        return gate;
    }
    public static int getGateSize() { return GATE_SIZE; }

    private static boolean between(int min, int target, int max) {
        return min <= target && target <= max;
    }

    public boolean isGate(Position position) {
        return between(gate.getX(), position.getX(), gate.getX() + GATE_SIZE) && position.getY() == gate.getY();
    }
    public boolean isFence(Position position) {
        int leftX = topLeftCorner.getX();
        int rightX = leftX + size.getWidth() - 1;
        int topY = topLeftCorner.getY();
        int botY = topY + size.getHeight() - 1;

        if (isGate(position))
            return false;

        boolean validFence = false;
        if (between(topY, position.getY(), botY))
            validFence = position.getX() == leftX || position.getX() == rightX;
        if (between(leftX, position.getX(), rightX) && !validFence)
            validFence = position.getY() == topY || position.getY() == botY;

        return validFence;
    }
}
