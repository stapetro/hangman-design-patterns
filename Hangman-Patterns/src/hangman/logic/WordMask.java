package hangman.logic;

import hangman.constants.HangmanConstants;
import hangman.domain.WordItem;
import hangman.persistence.PersistenceFacadeSingleton;

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents mask of the original word. Memento
 * 
 * @author Stanislav Petrov
 * 
 */
public class WordMask extends Observable {

	private WordItem wordItem;
	private String maskedWord;
	private StringBuilder usedLetters;
	private int totalMistakes;

	/**
	 * True - visible letter, false - otherwise.
	 */
	private boolean[] mask;
	/**
	 * Stores current revealed letters on every user step.
	 */
	private int numberOfrevealedLetters;
	private Character lastRevealedLetter;

	public WordMask(WordItem wordItem) {
		setWordItem(wordItem);
	}

	/**
	 * Sets original word and resets mask for it.
	 * 
	 * @param originalWord
	 */
	public void setWordItem(WordItem wordItem) {
		if (wordItem != null) {
			this.wordItem = wordItem;
		}
		initialize();
	}

	public WordItem getWordItem() {
		return this.wordItem;
	}

	public char getLastRevealedLetter() {
		return this.lastRevealedLetter;
	}

	public String getMaskedWord() {
		return this.maskedWord;
	}

	public int getNumberOfRevealedLetters() {
		return this.numberOfrevealedLetters;
	}

	public String getProberb() {
		return this.wordItem.getProverb();
	}

	/**
	 * 
	 * @param letter
	 *            Letter that is guessed from user.
	 * @return True - if user has revealed a letter, false - otherwise.
	 */
	public boolean revealLetter(char letter) {
		boolean revealedLetter = processLetter(letter);
		setChanged();
		notifyObservers();
		return revealedLetter;
	}

	/**
	 * Reveals left-most masked letter from word.
	 * 
	 * @return True if letter is revealed successful, false - otherwise.
	 */
	public boolean revealLetter() {
		char letterToReveal = getNextLetterToReveal();
		if (letterToReveal != HangmanConstants.MASK_SYMBOL) {
			return revealLetter(letterToReveal);
		}
		return false;
	}

	public boolean isWordRevealed() {
		if (this.maskedWord.indexOf(HangmanConstants.MASK_SYMBOL) < 0) {
			return true;
		} else {
			return false;
		}
	}

	public HangmanMemento saveToMemento() {
		return new HangmanMemento(this.wordItem.getId(), totalMistakes,
				usedLetters.toString());
	}

	public void restoreFromMemento(HangmanMemento memento) {
		this.wordItem = PersistenceFacadeSingleton.getInstance().getWord(
				memento.getSavedWordId());
		initialize();
		String savedUsedLetters = memento.getSavedUsedLetters();
		for (int index = 0; index < savedUsedLetters.length(); index++) {
			processLetter(savedUsedLetters.charAt(index));
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return String
				.format(
						"Masked word: %s, Mistakes: %d, Used letters: %s, Revealed letters: %d, Last revealed: %c",
						this.maskedWord, this.totalMistakes, this.usedLetters,
						this.numberOfrevealedLetters, this.lastRevealedLetter);
	}

	/**
	 * Gets next masked letter to be revealed.
	 * 
	 * @return Left-most letter to be revealed from the original word or mask
	 *         symbol if word mask is not consistent.
	 */
	private char getNextLetterToReveal() {
		for (int index = 0; index < this.mask.length; index++) {
			if (mask[index] == false) {
				return this.wordItem.getContent().charAt(index);
			}
		}
		return HangmanConstants.MASK_SYMBOL;
	}

	private void constructMaskedWord() {
		this.maskedWord = "";
		String originalWord = this.wordItem.getContent();
		for (int index = 0; index < mask.length; index++) {
			if (this.mask[index] == true) {
				this.maskedWord += originalWord.charAt(index);
			} else {
				this.maskedWord += HangmanConstants.MASK_SYMBOL;
			}
			this.maskedWord += ' ';
		}
	}

	private void initialize() {
		this.mask = new boolean[this.wordItem.getContent().length()];
		this.numberOfrevealedLetters = 0;
		this.totalMistakes = 0;
		this.lastRevealedLetter = null;
		this.usedLetters = new StringBuilder();
		constructMaskedWord();
	}

	private void saveUsedLetter(char letter) {
		this.usedLetters.append(letter);
	}

	private boolean processLetter(char letter) {
		if (isWordRevealed() == true) {
			return false;
		}
		String originalWord = this.wordItem.getContent();
		int firstLetterIndex = originalWord.indexOf(letter);
		this.numberOfrevealedLetters = 0;
		if (firstLetterIndex >= 0) {
			for (int index = firstLetterIndex; index < mask.length; index++) {
				if (letter == originalWord.charAt(index)
						&& mask[index] == false) {
					mask[index] = true;
					this.numberOfrevealedLetters++;
				}
			}
			constructMaskedWord();
			this.lastRevealedLetter = letter;
		}
		saveUsedLetter(letter);
		if (this.numberOfrevealedLetters > 0) {
			return true;
		} else {
			this.totalMistakes++;
			return false;
		}
	}

	public static class HangmanMemento implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2252490418505217405L;
		private final int wordId;
		private final String usedLetters;

		private HangmanMemento(int wordId, int mistakes, String usedLetters) {
			this.wordId = wordId;
			this.usedLetters = usedLetters;
		}

		private int getSavedWordId() {
			return this.wordId;
		}

		private String getSavedUsedLetters() {
			return this.usedLetters;
		}
	}
}
