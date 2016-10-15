package io.github.alvinnorin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class snake extends KeyAdapter implements Runnable {
	
	public static int x = 0;
	public static int y = 0;
	public static int dirX = 0;
	public static int dirY = 0;
	public static int lenght = 1;
	
	static HashMap<Integer, Integer> ownedX = new HashMap();
	static HashMap<Integer, Integer> ownedY = new HashMap();
	static HashMap<Integer, Integer> ownedT = new HashMap();

	public static void draw(Graphics g, int width, int height) {
        //g.fillRect(0, 0, 100, 100);
		//g.clearRect(0, 0, width, height);
		/*int matrixSize = 18;
		int blockSize = (height/matrixSize);
		g.setColor(Color.green);
		g.fillRect (x, y, blockSize, blockSize);*/
		//g.fillRect (8, 248, 240, 240);
		//g.drawString("Score: "+0, 0, 40);
		//g.fillRect (15, 50, 50, 200);
		//g.fillRect (15, 50, 50, 200);
	}
	
	public static void body() {
		int size = matrix.owned.get(1).length();
		if (!ownedX.containsKey(0) || !ownedY.containsKey(0)) {
			for (int c = 0; c != lenght; c ++) {
				ownedX.put(c, x);
				ownedY.put(c, y);
			}
		} else {
			for (int c = lenght-1; c != 0; c --) {
				//if (!matrix.contains(1, ownedX.get(c - 1), ownedY.get(c - 1)))
				if (!ownedX.containsKey(c) || !ownedY.containsKey(c)) {
					ownedX.put(c, ownedX.get(c - 1));
					ownedY.put(c, ownedY.get(c - 1));
				} else {
					ownedX.replace(c, ownedX.get(c - 1));
					ownedY.replace(c, ownedY.get(c - 1));
					System.out.println("rep"+c);
				}
			}
			/*if (lenght > 10) {
				for (int c = 2; c != lenght; c --) {
					if (x == ownedX.get(c) && y == ownedY.get(c))
						matrix.clear(1);
				}
			}*/
			ownedX.replace(0, x);
			ownedY.replace(0, y);
		}
	}
	
	public static void keyAlert(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_W || key.getKeyCode() == KeyEvent.VK_A ||
				key.getKeyCode() == KeyEvent.VK_S || key.getKeyCode() == KeyEvent.VK_D)
			dirX = 0; dirY = 0;
		if (key.getKeyCode() == KeyEvent.VK_W) dirY = -1;
		if (key.getKeyCode() == KeyEvent.VK_A) dirX = -1;
        if (key.getKeyCode() == KeyEvent.VK_S) dirY = +1;
        if (key.getKeyCode() == KeyEvent.VK_D) dirX = +1;
        if (key.getKeyCode() == KeyEvent.VK_SHIFT) lenght ++;
	}
	
	public static void maintain() {
		if ((time + 128) < System.currentTimeMillis()) {
			time = System.currentTimeMillis();
			x += dirX;
			y += dirY;
			if (x == -1) x = 0;
			if (y == -1) y = 0;
			if (x == 16) x = 15;
			if (y == 16) y = 15;
			matrix.put(1, 1, 5);
			body();
			matrix.clear(1);
			for (int c = 0; c != lenght; c ++) {
				matrix.put(1, ownedX.get(c), ownedY.get(c));
				System.out.println("draw"+c+" "+ownedX.get(c)+" "+ownedY.get(c));
			}
			if (food.hasEaten(1)) {
				food.maintain();
				lenght ++;
			}
				
			for (int c = 0; c != food.amount; c ++) {
				matrix.put(1, food.x.get(c), food.z.get(c));
			}
				
		}
	}
	
	public static long time = 0;

	@Override
	public void run() {
		long cycleTime = System.currentTimeMillis();
		food.maintain();
		while (true) {
			cycleTime = cycleTime + 60;
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
