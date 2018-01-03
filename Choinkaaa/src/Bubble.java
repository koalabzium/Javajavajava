package Choinkaaa.src;

import Choinkaaa.src.XmasShape;

import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Bubble(int x, int y, double scale, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }



    @Override
    public void render(Graphics2D g2d) {

        g2d.setColor(fillColor);
        g2d.fillOval(x,y,15,15);
        g2d.setColor(lineColor);
        g2d.drawOval(x,y,15,15);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(scale, scale);


    }
}
