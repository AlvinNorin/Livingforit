package io.github.alvinnorin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public class ui extends JFrame implements Runnable {
	
	static boolean doInitialization = false;
	
    Image dbImage;
    Graphics dbg;

	public void initializationListener() {
		if (doInitialization) { doInitialization = false;
			this.setTitle("Snake");
			this.setSize(256, 256);
			this.setResizable(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBackground(Color.black);
		    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		    this.setUndecorated(true);
		    this.setVisible(true);
		    GraphicsEnvironment.getLocalGraphicsEnvironment().
            getDefaultScreenDevice().setFullScreenWindow(this);
			this.addKeyListener(new keyUi());
		}
	}

	public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage, 0, 0, this);
		/*g.clearRect(0, 0, this.getHeight(), this.getWidth());
		int beginY = 0;
		int beginX = 0;
		int matrixSize = 18;
		int blockSize = (this.getHeight()/matrixSize);
		g.setColor(Color.green);
		g.fillRect (beginX, beginY, blockSize*18, blockSize);
		g.fillRect (beginX, (blockSize*17), blockSize*18, blockSize);
		g.fillRect (beginX, beginY, blockSize, blockSize*18);
		g.fillRect (blockSize*17, beginY, blockSize, blockSize*18);
		//g.fillRect (8, 248, 240, 240);
		//g.drawString("Score: "+0, 0, 40);
		//g.fillRect (15, 50, 50, 200);
		//g.fillRect (15, 50, 50, 200);*/
		repaint();
	}
	
    public void draw(Graphics g){
		matrix.draw(g, this.getWidth(), this.getHeight());
		snake.draw(g, this.getWidth(), this.getHeight());
        repaint();
    }
	
    public class keyUi extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
        	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        	snake.keyAlert(e);
        }
        @Override
        public void keyReleased(KeyEvent e){

        }
    }
	
	public static void initialize() {
		doInitialization = true;
	}
	
	public void maintain() {

	}

	@Override
	public void run() {
		long cycleTime = System.currentTimeMillis();
		while (true) {
			initializationListener();
			maintain();
			cycleTime = cycleTime + 30;
			long difference = cycleTime - System.currentTimeMillis();
			maintain();
			//System.out.println("m");
			try {
				Thread.sleep(Math.max(0, difference));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
