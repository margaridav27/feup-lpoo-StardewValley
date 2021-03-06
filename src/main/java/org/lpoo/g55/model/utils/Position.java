package org.lpoo.g55.model.utils;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Position getUp() {
        return new Position(this.x, this.y - 1);
    }
    public Position getDown() {
        return new Position(this.x, this.y + 1);
    }
    public Position getLeft() {
        return new Position(this.x - 1, this.y);
    }
    public Position getRight() {
        return new Position(this.x + 1, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return this.x == position.x && this.y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
