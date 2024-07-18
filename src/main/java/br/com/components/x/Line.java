package br.com.components.x;

import br.com.components.core.JLComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Line extends JLComponent {


    @Override
    public BufferedImage render() {
        BufferedImage image = new BufferedImage(getSize().getWidth(), getSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillRect(0,0, getSize().getWidth(), getSize().getHeight());

        return image;
    }

    @Override
    public void update() {

    }
}
