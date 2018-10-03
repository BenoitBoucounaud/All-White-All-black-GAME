package main;

import frames.Jeu;

public class Application {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				new Jeu() ;	
			}
		});
	}
}
