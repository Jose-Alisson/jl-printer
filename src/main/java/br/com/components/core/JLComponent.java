package br.com.components.core;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class JLComponent {

    private List<JLComponent> components = new ArrayList<>();
    private Color backgroundColor = new Color(255,255,255, 0);
    private Color color = Color.BLACK;
    private JLLayout layout;
    private Orientable orientable = Orientable.LEFT;
    private Size size;
    private Font font = new Font("Arial", Font.BOLD, 12);;
    private Position position = new Position(0,0);

    public abstract BufferedImage render();

    public abstract void update();

    public void add(JLComponent jlComponent){
        this.components.add(jlComponent);
    }

    public List<JLComponent> getComponents() {
        return components;
    }

    public void setComponents(List<JLComponent> components) {
        this.components = components;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public JLLayout getLayout() {
        return layout;
    }

    public void setLayout(JLLayout layout) {
        this.layout = layout;
    }

    public Orientable getOrientable() {
        return orientable;
    }

    public void setOrientable(Orientable orientable) {
        this.orientable = orientable;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
