package hangman.logic;

import hangman.domain.WordItem;
import hangman.persistence.IPersistenceFacade;
import hangman.persistence.PersistenceFacadeSingleton;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test Random Word Generation
 */
public class WordGeneratorTest {

	@Test
	public void testRandomWordGeneration() {
		WordItem word = WordGenerator.getRandomWordItem();
		List<WordItem> wordsList = getAllWords();

		boolean isWordFromList = false;
		String generatedWordContent = word.getContent();
		for (WordItem singleWord : wordsList) {
			String wordListContent = singleWord.getContent();
			if (generatedWordContent.equals(wordListContent)) {
				isWordFromList = true;
				break;
			}
		}

		Assert.assertTrue(isWordFromList);
	}

	private List<WordItem> getAllWords() {
		IPersistenceFacade persistenceFacade = PersistenceFacadeSingleton
				.getInstance();
		List<WordItem> wordsList = persistenceFacade.getWords();

		return wordsList;
	}

}
