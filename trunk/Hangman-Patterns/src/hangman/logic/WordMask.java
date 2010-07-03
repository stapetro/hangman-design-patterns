package hangman.logic;

import hangman.constants.HangmanConstants;

/**
 * Represents mask of the original word.
 * 
 * @author Stanislav Petrov
 * 
 */
public class WordMask {

	private String originalWord;
	private String maskedWord;
	/**
	 * True - visible letter, false - otherwise.
	 */
	private boolean[] mask;
	/**
	 * Stores current revealed letters on every user step.
	 */
	private int numberOfrevealedLetters;
	private Character lastRevealedLetter;

	public WordMask(String originalWord) {
		this.originalWord = originalWord;
		initialize();
		constructMaskedWord();
	}

	/**
	 * Sets original word and resets mask for it.
	 * 
	 * @param originalWord
	 */
	public void setOriginalWord(String originalWord) {
		if (originalWord != null) {
			this.originalWord = originalWord;
		} else {
			this.originalWord = "";
		}
		initialize();
		constructMaskedWord();
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

	/**
	 * 
	 * @param letter
	 *            Letter that is guessed from user.
	 * @return True - if user has revealed a letter, false - otherwise.
	 */
	public boolean revealLetter(char letter) {
		int firstLetterIndex = this.originalWord.indexOf(letter);
		if (firstLetterIndex >= 0) {
			this.numberOfrevealedLetters = 0;
			for (int index = firstLetterIndex; index < mask.length; index++) {
				if (letter == this.originalWord.charAt(index)
						&& mask[index] == false) {
					mask[index] = true;
					this.numberOfrevealedLetters++;
				}
			}
			constructMaskedWord();
			this.lastRevealedLetter = letter;
			return true;
		}
		return false;
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
	
	/**
	 * Gets next masked letter to be revealed.
	 * 
	 * @return Left-most letter to be revealed from the original word or mask
	 *         symbol if word mask is not consistent.
	 */
	private char getNextLetterToReveal() {
		for (int index = 0; index < this.mask.length; index++) {
			if (mask[index] == false) {
				return this.originalWord.charAt(index);
			}
		}
		return HangmanConstants.MASK_SYMBOL;
	}	

	private void constructMaskedWord() {
		this.maskedWord = "";
		for (int index = 0; index < mask.length; index++) {
			if (this.mask[index] == true) {
				this.maskedWord += this.originalWord.charAt(index);
			} else {
				this.maskedWord += HangmanConstants.MASK_SYMBOL;
			}
			this.maskedWord += ' ';
		}
	}

	private void initialize() {
		this.mask = new boolean[this.originalWord.length()];
		this.numberOfrevealedLetters = 0;
		this.lastRevealedLetter = null;
	}
}
