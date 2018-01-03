package Choinkaaa.src;

import Choinkaaa.src.Brunch;
import Choinkaaa.src.Bubble;
import Choinkaaa.src.XmasShape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DrawPanel extends JPanel{

    List<XmasShape> shapes = new ArrayList<>();
    DrawPanel(){
        setBackground(new Color(0,0,50));
//        setOpaque(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.ITALIC, 50));
        g.drawString("Wesołych Swiąt!", 80, 100);

        Color colors[]={Color.blue,new Color(255,0,0),new Color(0,255,255),Color.yellow};
        Random generator = new Random();
        Trunk t = new Trunk(495,100,505,620);
        t.draw((Graphics2D)g);

        for(int i=0;i<20;i++){

            Brunch br = new Brunch(500,100+25*i,20+20*i,1,Color.green,Color.black,true);
            br.draw((Graphics2D)g);
            Brunch br2 = new Brunch(500,100+25*i,20+20*i,1,Color.green,Color.black,false);
            br2.draw((Graphics2D)g);
            int ranx = generator.nextInt(br.length) + br.x;
            Bubble b = new Bubble(ranx,br.y,1,Color.black,colors[generator.nextInt(4)]);
            b.draw((Graphics2D)g);
            Bubble b2 = new Bubble(ranx-br.length,br.y,1,Color.black,colors[generator.nextInt(4)]);
            b2.draw((Graphics2D)g);
            if(i>10){
                int ranx2 = generator.nextInt(br.length) + br.x;
                Bubble b3 = new Bubble(ranx2,br.y,1,Color.black,colors[generator.nextInt(4)]);
                b3.draw((Graphics2D)g);
                Bubble b4 = new Bubble(ranx2-br.length,br.y,1,Color.black,colors[generator.nextInt(4)]);
                b4.draw((Graphics2D)g);
            }
            int ranx3 = generator.nextInt(br.length) + br.x;
            Light l1 = new Light(ranx3, br.y);
            Light l2 = new Light(ranx3-br.length,br.y);
            l1.draw((Graphics2D)g);
            l2.draw((Graphics2D)g);
        }
        Star s = new Star(555,50);
        s.draw((Graphics2D)g);


    }




}
