package Kulki;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    AnimationThread animation;

    static class Ball {
        int x;
        int y;
        double vx;
        double vy;
        Color color;
        int size;

        Ball() {
            Random r = new Random();

            this.x = r.nextInt(700);
            this.y = r.nextInt(700);
            this.color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            this.vx = (r.nextInt(2) == 1) ? -10 : 10;
            this.vy = (r.nextInt(2) == 1) ? -10 : 10;
            this.size = r.nextInt(15)+15;
        }



        public void draw(Graphics2D g2d) {
            AffineTransform saveAT = g2d.getTransform();
            this.render(g2d);
            g2d.setTransform(saveAT);
        }

        public void render(Graphics2D g2d) {
             g2d.setColor(this.color);

            g2d.fillOval(x,y,size,size);
        }
    }

    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread {
        public void run() {
            while (true) {
                balls.forEach((Ball ball) -> {
                    ball.x += ball.vx;
                    ball.y += ball.vy;
                    if(ball.x>=(660)  || ball.x<=0 ) ball.vx *= -1;
                    if(ball.y>=(600)  || ball.y<=0) ball.vy *= -1;
                    balls.forEach(ball2 ->{
                        if(((ball2.x>ball.x && ball2.x<ball.x+ball.size) && (ball2.y>ball.y && ball2.y<ball.y+ball.size))||((ball.x>ball2.x && ball.x<ball2.x+ball2.size) && (ball.y>ball2.y && ball.y<ball2.y+ball2.size))){
                            ball.vx *=-1;
                            ball.vy *=-1;
                            ball2.vx *=-1;
                            ball2.vy *= -1;
                        }
                    });
                });



                repaint();
                // wywołaj repaint
                try {
                    animation.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BouncingBallsPanel() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
    }

    void onStart() {
        this.animation = new AnimationThread();
        this.animation.start();
    }

    void onStop() {
        this.animation.suspend();
    }

    void onPlus() {
        this.balls.add(new Ball());
    }

    void onMinus() {
       // System.out.println("Remove a ball");

        this.balls.remove(0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        balls.forEach(ball -> ball.draw((Graphics2D) g));
    }
}