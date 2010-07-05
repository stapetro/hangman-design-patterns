package hangman.logic;

import hangman.domain.WordItem;
import hangman.persistence.IPersistenceFacade;
import hangman.persistence.PersistenceFacadeSingleton;

import java.util.List;
import java.util.Random;

/**
 * Words random generator class
 */
public class WordGenerator {

	/**
	 * Generate a random word based on the language settings
	 * 
	 * @return random WordItem
	 */
	public static WordItem getRandomWordItem() {
		IPersistenceFacade persistenceFacade = PersistenceFacadeSingleton
				.getInstance();
		List<WordItem> wordItemsList = persistenceFacade.getWords();

		if (wordItemsList == null) {
			return new WordItem();
		} else {
			WordItem wordItem = getRandomWordItemFromList(wordItemsList);
			return wordItem;
		}

	}

	/**
	 * Get random word from the provided list
	 * 
	 * @param wordItemsList
	 *            list of words to search for
	 * @return a random word form the list
	 */
	private static WordItem getRandomWordItemFromList(List<WordItem> wordItemsList) {
		Random randomGenerator = new Random();
		int wordItemsLength = wordItemsList.size();
		int randomPosition = randomGenerator.nextInt(wordItemsLength);

		WordItem randomWord = wordItemsList.get(randomPosition);
		return randomWord;
	}

}
