package io.github.alvinnorin;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Main extends JFrame {

    //Double buffering
    Image dbImage;
    Graphics dbg;

    //Ball objects
    static Ball b = new Ball(193, 143);

    //Variables for screen size
    int
    GWIDTH = 400,
    GHEIGHT = 300;
    //Dimension of GWIDTH*GHEIGHT
    Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);

    //Create constructor to spawn window
    public Main(){
        this.setTitle("Pong Game");
        this.setSize(screenSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
        this.addKeyListener(new AL());
    }

    public static void main(){
        Main m = new Main();
        //Create and start threads
        Thread ball = new Thread(b);
        ball.start();
        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);
        p1.start();
        p2.start();
    }

    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    public void draw(Graphics g){
        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);

        g.setColor(Color.WHITE);
        g.drawString(""+b.p1Score, 15, 50);
        g.drawString(""+b.p2Score, 370, 50);

        repaint();
    }

    ////////EVENT LISTENER CLASS/////////
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);
        }
    }
    ///////END EVENT LISTENER CLASS/////

}