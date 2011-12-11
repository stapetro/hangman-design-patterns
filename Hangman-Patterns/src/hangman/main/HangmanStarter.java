package hangman.main;

import hangman.presentation.HangmanFrame;

import javax.swing.JFrame;

public class HangmanStarter {
	
	public HangmanStarter() {
		HangmanFrame frame = new HangmanFrame();
		//Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Size the frame.
		frame.pack();
		//Show it.
		frame.setVisible(true);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new HangmanStarter();
	}
}
