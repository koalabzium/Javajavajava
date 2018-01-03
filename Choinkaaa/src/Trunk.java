package Choinkaaa.src;


import java.awt.*;

public class Trunk implements XmasShape {
    int x1;
    int y1;
    int x2;
    int y2;

    public Trunk(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(128,55,27));
        int x[]={x1,x2,x2,x1};
        int y[]={y1,y1,y2,y2};
        g.fillPolygon(x,y,x.length);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(1,1);


    }
}
