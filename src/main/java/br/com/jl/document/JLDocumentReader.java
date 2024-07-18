package br.com.jl.document;


import br.com.components.core.JLComponent;
import br.com.components.core.Orientable;
import br.com.components.core.Position;
import br.com.components.core.Size;
import br.com.components.x.Line;
import br.com.components.x.Text;
import br.com.components.x.Title;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JLDocumentReader  {
    private List<JLComponent> components;
    private String[] lines;
    private Dimension size;

    private Pattern titleRegex = Pattern.compile("^\\s*(#{1,6}).*$");
    private Pattern lineRegex = Pattern.compile("^___$|^\\*\\*\\*$");

    public JLDocumentReader(Dimension size,String sintaxe) {
        lines = sintaxe.split("\n");
        this.size = size;
        init();
        update();
    }

    public void init(){
        components = new ArrayList<>();

        for (int j = 0;j < this.lines.length;j++){
            Matcher titleMatcher = titleRegex.matcher(lines[j]);
            Matcher lineMatcher = lineRegex.matcher(lines[j]);

            if (titleMatcher.find()){
                lines[j] = lines[j].replace(titleMatcher.group(1), "");
                Title title = new Title(lines[j]);
                title.setSize(new Size(size.width,0));
                components.add(title);
            } else if (lineMatcher.find()) {
                Line line = new Line();
                line.setSize(new Size(size.width, 2));
                line.setBackgroundColor(Color.BLACK);
                this.components.add(line);
            } else {
                Text text = new Text(lines[j]);
                text.setSize(new Size(size.width, 0));
                this.components.add(text);
            }
        }
    }

    public void render(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        components.forEach(comp -> {
            graphics2D.drawImage(comp.render(), comp.getPosition().getX(),comp.getPosition().getY(), null);
        });
    }

    public void update(){
        AtomicInteger height = new AtomicInteger();
        components.forEach(comp -> {
            comp.update();
            comp.setPosition(new Position(comp.getPosition().getX(), height.get()));
            height.set(comp.getPosition().getY() + comp.getSize().getHeight());
        });
    }

    public void setSize(Dimension size) {
        this.size = size;
    }
}
