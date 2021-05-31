package org.lpoo.g55.model.utils;

public class Size {
    public static final Size DEFAULT_WORLD_SIZE = new Size(50, 30);

    private int width;
    private int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = Math.max(1, width);
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = Math.max(1, height);
    }
}
