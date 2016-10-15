package io.github.alvinnorin;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class food {

	static HashMap<Integer, Integer> free = new HashMap(); //list:id of eaten food
	static HashMap<Integer, Integer> x = new HashMap(); //id of food:x cordinates of given food
	static HashMap<Integer, Integer> z = new HashMap(); //id of food:z cordinates of given food
	static HashMap<Integer, Integer> c = new HashMap(); //id of food:color of given food
	static int amount = 0;
	static int minus = 0;
	
	public static void spawn() {
		//command.plugin.getLogger().info("spawn");
		//for (int count )
		//if (x.containsKey(key))
		int newX = ThreadLocalRandom.current().nextInt(0, 15 + 1);
		int newZ = ThreadLocalRandom.current().nextInt(0, 15 + 1);
		int newC = ThreadLocalRandom.current().nextInt(0, 15 + 1);
		amount -= minus;
		int xSize = x.size();
		int zSize = z.size();
		int cSize = c.size();
		if (minus > 0) {
			xSize = free.get(minus);
			zSize = free.get(minus);
			cSize = free.get(minus);
		}
		x.put(xSize, newX);
		z.put(zSize, newZ);
		c.put(cSize, newC);
		if (minus > 0)
			minus --;
		amount ++;
	}
	
	public static boolean hasEaten(int player) {
		boolean eaten = false;
		for (int count=0; count!=amount; count ++) {
			//player.sendMessage("..");
        	int foodX = 0;
        	int foodZ = 0;
    		foodX = x.get(count);
    		foodZ = z.get(count);
    		System.out.println("food"+foodX+" "+snake.x);
			if (matrix.contains(player, foodX, foodZ)) {
				//command.plugin.getLogger().info(player.getName()+"food");
				minus ++;
				free.put(minus, count);
				x.remove(count);
				z.remove(count);
				c.remove(count);
				eaten = true;
			}
		}
		return eaten;
		
	}
	
	public static void clean(int player) {
    	for (int count=0; count!=amount; count ++) {
        	int foodX = 1;
        	int foodZ = 1;
        	for (int a = 0; a != foodX-256; a--) {
        		for (int b = 0; a != foodZ-256; a--) {
		        	/*for (int online : Bukkit.getOnlinePlayers()) {
		        		online.getPlayer().sendBlockChange(new Location(command.plugin.getServer().getWorld("world"), (double)(a), 4.0, (double)(b)), 0, (byte) 0);
		        	}*/
        		}
        	}
    	}
	}
	
	public static boolean isMaintaining = false;
    @SuppressWarnings("deprecation")
	public static void maintain() {
    	if (!isMaintaining)
            	//command.plugin.getLogger().info("food");
            	isMaintaining = true;
            	spawn();
            	//for (int count=0; count!=amount; count ++) {
            		//command.plugin.getLogger().info("1food");
                	int foodX = 0;
                	int foodZ = 0;
                	int foodC = 0;
                	try {
	            		foodX -= x.get(amount-1);
	            		foodZ -= z.get(amount-1);
	            		foodC = c.get(amount-1);
	                	matrix.claim(0, foodX, foodZ);
	                	//online.getPlayer().sendMessage(foodX+" "+foodZ+" "+foodC+" "+amount);
                	} catch (java.lang.NullPointerException e) {
                		//command.plugin.getLogger().info("Food recycling not in sync: "+e);
                	}
            	//}
    }
    
}
