package br.com.components.x;

import br.com.components.core.Orientable;

import java.awt.*;

public class Title extends Text{

    public Title(String text) {
        super(text);
        setBackgroundColor(Color.BLACK);
        setColor(Color.WHITE);
        setOrientable(Orientable.CENTER);
        setFont(new Font("Bahnschrift", Font.BOLD, 24));
    }
}
