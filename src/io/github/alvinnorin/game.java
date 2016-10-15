package io.github.alvinnorin;

public class game {

	public static void main(String[] args) {
		initialize();
	}
	
	public static void initialize() {
		(new Thread(new ui())).start();
		ui.initialize();
		(new Thread(new matrix())).start();
		(new Thread(new snake())).start();
	}

}
