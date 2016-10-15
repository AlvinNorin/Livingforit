package io.github.alvinnorin;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class matrix implements Runnable {

	public static int[] matrix = new int[16*16];
	
	static HashMap<Integer, Integer> owned_x = new HashMap();
	static HashMap<Integer, Integer> owned_z = new HashMap();
	static HashMap<Integer, Integer> owned_mass = new HashMap();
	static HashMap<Integer, Boolean> claim_done = new HashMap();
	static HashMap<Integer, String> owned = new HashMap();
	
	static long cycleTime = System.currentTimeMillis();
	static long sTps = 0;
	static long tps = 0;
	
	public static void claim(int player, int x, int y) {
		try {
			owned_x.containsKey(player);
			owned_z.containsKey(player);
			owned_mass.containsKey(player);
		} catch (NullPointerException e) {
			owned_x.put(player, 0);
			owned_z.put(player, 0);
			owned_mass.put(player, 0);
        }
		owned_x.put(player, 1);
		owned_x.put(player, 0);
		owned_x.put(player, 2);
		owned_x.put(player, 4);
	}
	
	public static void put(int player, int x, int z) {
		String current = owned.get(player);
		if (current == null) current = "";
		owned.put(player, current+","+x+" "+z+";");
		//player.sendMessage(owned.get(player));
	}
	
	public static void clear(int player) {
		try {
			owned.get(player);
		} catch (NullPointerException e) {
			owned.put(player, "");
        }
		owned.put(player, "");
	}
	
	public static void clear(int player, int x, int z) {
		try {
			owned.get(player);
		} catch (NullPointerException e) {
			owned.put(player, "");
        }
		if (owned.get(player).contains(","+x+" "+z+";")) {
			owned.put(player, owned.get(player).replace(","+x+" "+z+";", ""));
		}
		
	}
	
	public static void get(int player) {
		String[] s1 = owned.get(player).split(",");
		for (int c = 0; c < s1.length; c++) {
			
		}
	}
	
	public static boolean contains(int player, int x, int z) {
		boolean rtn = false;
		try {
			rtn = owned.get(player).contains("," + x + " " + z + ";");
		} catch (java.lang.NullPointerException e) {
			
		}
		return rtn;
	}

	public static void draw(Graphics g, int width, int height) {
        //g.fillRect(0, 0, 100, 100);
		//g.clearRect(0, 0, width, height);
		int beginY = 0;
		int beginX = 0;
		int matrixSize = 18;
		int blockSize = (height/matrixSize);
		g.setColor(Color.green);
		g.fillRect (beginX, beginY, blockSize*18, blockSize);
		g.fillRect (beginX, (blockSize*17), blockSize*18, blockSize);
		g.fillRect (beginX, beginY, blockSize, blockSize*18);
		g.fillRect (blockSize*17, beginY, blockSize, blockSize*18);
		//clear(1);
		//put(1, 2, 1);
		//put(1, 2, 2);
		System.out.println(owned.values()+" "+contains(1, 1, 1));
		for (int c1 = 0; c1 != 16; c1 ++) {
			for (int c2 = 0; c2 != 16; c2 ++) {
				//if (c1 == 1 && c2 == 1) System.out.println("1"+contains(1, c2, c1));
				if (contains(1, c2, c1)) {g.setColor(Color.green);System.out.println(c2 + " " + c1);}
				else g.setColor(Color.black);
				g.fillRect (blockSize*(c2+1), blockSize*(c1+1), blockSize, blockSize);
				//g.fillRect (blockSize, blockSize, blockSize*(c2+2), blockSize*(c1+2));
			}
		}
		cycleTime = cycleTime + 60;
		long difference = cycleTime - System.currentTimeMillis();
		g.setColor(Color.black);
		if (sTps <= System.currentTimeMillis()) {
			sTps = (cycleTime + 940);
			tps = (sTps - System.currentTimeMillis())/16;
			
		} else {
			g.drawString("Debug: "+" FPS: "+tps+" "+snake.dirX+" "+snake.dirY+" ", 0, 40);
			cycleTime = System.currentTimeMillis();
		}
		/*for (int c1 = 0; c1 != 15; c1 ++) {
			for (int c2 = 0; c2 != 15; c2 ++) {
				if (matrix[c1*c2] == 1) g.setColor(Color.green);
				if (matrix[c1*c2] == 0) g.setColor(Color.black);
				g.fillRect (blockSize, blockSize, blockSize*(c2+2), blockSize*(c1+2));
			}
		}*/
		//g.fillRect (8, 248, 240, 240);
		//g.fillRect (15, 50, 50, 200);
		//g.fillRect (15, 50, 50, 200);
	}
	
	public static void maintain() {
		
	}

	@Override
	public void run() {
		long cycleTime = System.currentTimeMillis();
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
