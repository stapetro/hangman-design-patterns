package hangman.logic;

import hangman.domain.WordItem;
import hangman.domain.config.LevelItem;
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
		LevelItem levelItem = persistenceFacade.getCurrentLevel();
		int minWordLength = levelItem.getMinWordLength();
		int maxWordLength = levelItem.getMaxWordLength();
		List<WordItem> wordItemsList = persistenceFacade.getWords();

		if (wordItemsList == null) {
			return new WordItem();
		} else {
			WordItem wordItem = null;
			String wordContent = null;
			do {
				wordItem = getRandomWordItemFromList(wordItemsList);
				if ((minWordLength == 0 && maxWordLength == 0)
						|| minWordLength > maxWordLength) {
					break;
				}
				wordContent = wordItem.getContent();
				if ((wordContent != null)) {
					int contentLength = wordContent.length();
					if (contentLength >= minWordLength
							&& contentLength <= maxWordLength) {
						break;
					}
				}
			} while (true);
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
	private static WordItem getRandomWordItemFromList(
			List<WordItem> wordItemsList) {
		Random randomGenerator = new Random(System.currentTimeMillis());
		int wordItemsLength = wordItemsList.size();
		int randomPosition = randomGenerator.nextInt(wordItemsLength);

		WordItem randomWord = wordItemsList.get(randomPosition);
		return randomWord;
	}

}
