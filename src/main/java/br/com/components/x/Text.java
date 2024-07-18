package br.com.components.x;

import br.com.components.core.JLComponent;
import br.com.components.core.Size;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Text extends JLComponent {

    private String[] text;
    private String txt;

    public Text(String text) {
        this.txt = text;
    }

    @Override
    public BufferedImage render() {
        BufferedImage image = new BufferedImage(getSize().getWidth(), getSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();
        //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setFont(getFont());
        graphics2D.setColor(getBackgroundColor());
        graphics2D.fillRect(0, 0, getSize().getWidth(), getSize().getHeight());
        graphics2D.setColor(getColor());

        switch (getOrientable()) {
            case LEFT -> {
                int lineHeight = graphics2D.getFontMetrics().getHeight();
                for (int i = 0; i < text.length; i++) {
                    graphics2D.drawString(text[i], 0, (i * lineHeight) + lineHeight);
                }
            }
            case RIGHT -> {
                int lineHeight = graphics2D.getFontMetrics().getHeight();
//                int y = 0;
                for (int i = 0; i < text.length ;i++) {
                    int lineWidth = graphics2D.getFontMetrics().stringWidth(text[i]);
                    int x = getSize().getWidth() - lineWidth; // Margem de 10 pixels da borda direita
                    graphics2D.drawString(text[i], x, (i * lineHeight) + lineHeight);
                }
            }
            case CENTER -> {
                int lineHeight = graphics2D.getFontMetrics().getHeight();
                int y = (getSize().getHeight() - text.length * lineHeight) / 2;

                for (int i = 0; i < text.length ;i++) {
                    int lineWidth = graphics2D.getFontMetrics().stringWidth(text[i]);
                    int x = (getSize().getWidth() - lineWidth) / 2;
                    graphics2D.drawString(text[i], x, (i * lineHeight) + lineHeight);
                    y += lineHeight;
                }
            }
        }

        return image;
    }

    @Override
    public void update() {
        this.wrapText();
        this.resizeHeight();
    }

    public void wrapText() {
        StringBuilder sb = new StringBuilder();
        // Divide o texto em palavras
        String[] words = txt.split(" ");
        int currentLineWidth = 0;

        for (String word: words){
            if((currentLineWidth + getFontMetrics(getFont()).stringWidth(word))  > getSize().getWidth()){
                sb.append("\n");
                currentLineWidth = 0;
            }
            sb.append(word).append(" ");
            currentLineWidth += getFontMetrics(getFont()).stringWidth(word);
        }

        this.text = sb.toString().split("\n");
    }

    public void resizeHeight() {
        FontMetrics fontMetrics = getFontMetrics(getFont());
        int lineHeight = fontMetrics.getHeight();
        this.setSize(new Size(getSize().getWidth(), lineHeight * text.length + lineHeight / 2));
    }

    public static FontMetrics getFontMetrics(Font font) {
        Graphics graphics = new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_ARGB).getGraphics();
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        graphics.dispose();
        return fontMetrics;
    }
}
