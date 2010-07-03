package hangman.main;

import hangman.constants.HangmanConstants;
import hangman.logic.config.ConfigurationParser;
import hangman.logic.xml.XmlManager;

public class HangmanStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String word = "Stanislav";
		// WordMask wordMask = new WordMask(word);
		// boolean success = wordMask.revealLetter();
		// System.out.println(((success) ? "" : "Not") +
		// "Reveal next letter...\n"
		// + wordMask.getMaskedWord());
		// success = wordMask.revealLetter('a');
		// System.out.println(((success) ? "" : "Not") +
		// "Reveal next letter...\n"
		// + wordMask.getMaskedWord());
		// success = wordMask.revealLetter('p');
		// System.out.println(((success) ? "" : "Not") +
		// "Reveal next letter...\n"
		// + wordMask.getMaskedWord());
		ConfigurationParser parser = new ConfigurationParser();
		System.out.println(parser.getAttributeValue("categories"));
	}

}
