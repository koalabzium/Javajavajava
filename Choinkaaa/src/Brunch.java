package Choinkaaa.src;

import Choinkaaa.src.XmasShape;

import java.awt.*;
import java.util.Random;

public class Brunch implements XmasShape {

    int x;
    int y;
    int length;
    double scale;
    Color needleColor;
    Color brunchColor;
    boolean right = true;

    public Brunch(int x, int y, int length, double scale, Color needleColor, Color branchColor, boolean right) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.scale = scale;
        this.needleColor = needleColor;
        this.brunchColor = branchColor;
        this.right = right;
    }


    @Override
    public void render(Graphics2D g2d) {

        Random generator = new Random();
        if(right){
            g2d.setColor(brunchColor);
            g2d.drawLine(x,y,x+length,y);
            g2d.setColor(needleColor);
        for(int i=0;i<length;i+=3){
            int ran = generator.nextInt(10) + 4;
            g2d.drawLine(x+i,y,x+3+i,y+ran);

        }
        for(int i=0;i<length;i+=3){
            int ran = generator.nextInt(10) + 4;
            g2d.drawLine(x+i,y,x+3+i,y-ran);
        }}

        else{
            g2d.setColor(brunchColor);
            g2d.drawLine(x,y,x-length,y);
            g2d.setColor(needleColor);
            for(int i=length;i>0;i-=3){
                int ran = generator.nextInt(10) + 4;
                g2d.drawLine(x-i,y,x-3-i,y-ran);

            }
            for(int i=0;i<length;i+=3){
                int ran = generator.nextInt(10) + 4;
                g2d.drawLine(x-i,y,x-3-i,y+ran);
            }
        }
        g2d.drawLine(x,y,x+2,y+10);


    }

    @Override
    public void transform(Graphics2D g2d) {

        g2d.scale(scale,scale);


    }
}
