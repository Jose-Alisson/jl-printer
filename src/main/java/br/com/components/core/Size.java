package br.com.components.core;

public class Size {
    private int width;
    private int height;

    private int maxWidth;
    private int maxHeight;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;

        this.maxWidth = width;
        this.maxHeight = height;
    }

    public void setMaxSize(int width, int height){
        this.maxWidth = width;
        this.maxHeight = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }
}
