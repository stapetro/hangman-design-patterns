package hangman.main;

import hangman.logic.WordMask;

public class HangmanStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String word = "Stanislav";
		WordMask wordMask = new WordMask(word);
		boolean success = wordMask.revealLetter();
		System.out.println(((success) ? "" : "Not") + "Reveal next letter...\n"
				+ wordMask.getMaskedWord());
		success = wordMask.revealLetter('a');
		System.out.println(((success) ? "" : "Not") + "Reveal next letter...\n"
				+ wordMask.getMaskedWord());
		success = wordMask.revealLetter('p');
		System.out.println(((success) ? "" : "Not") + "Reveal next letter...\n"
				+ wordMask.getMaskedWord());
	}

}
