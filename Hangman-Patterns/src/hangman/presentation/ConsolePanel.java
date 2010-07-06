package hangman.presentation;

import hangman.languages.LanguageResourcesFactory;
import hangman.logic.WordMask;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel extends JPanel implements Observer {

	private static final String ERROR_MSG = "errorMessage";
	private static final String CURRENT_WORD_MSG = "currentWordMessage";
	private static final String WORD_REVEALED_MSG = "wordRevealedMessage";
	private static final String PROVERB_MSG = "proverbMessage";
	private static final String REVEAL_LETTER_MSG = "revealLetterMsg";
	private static final String LETTERS_MSG = "lettersMsg";
	private static final String NO_REVEALED_LETTERS_MSG = "noRevealedLettersMsg";
	private static final String HUNG_MSG = "hungMsg";

	private static final long serialVersionUID = 1L;
	private JScrollPane consoleScrollPane = null;
	private JTextArea consoleTextArea = null;

	private ResourceBundle resourceBundle;

	/**
	 * This is the default constructor
	 */
	public ConsolePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		resourceBundle = LanguageResourcesFactory.getLanguageResource();

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		this.setSize(203, 258);
		this.setLayout(new GridBagLayout());
		this.add(getConsoleScrollPane(), gridBagConstraints);
	}

	/**
	 * This method initializes consoleScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getConsoleScrollPane() {
		if (consoleScrollPane == null) {
			consoleScrollPane = new JScrollPane();
			consoleScrollPane.setViewportView(getConsoleTextArea());
		}
		return consoleScrollPane;
	}

	/**
	 * This method initializes consoleTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getConsoleTextArea() {
		if (consoleTextArea == null) {
			consoleTextArea = new JTextArea();
		}
		return consoleTextArea;
	}

	/**
	 * Handle the update actions when the WordMask is changed
	 */
	@Override
	public void update(Observable wordMaskObservable, Object arg) {
		WordMask wordMask;
		if (wordMaskObservable instanceof WordMask) {
			wordMask = (WordMask) wordMaskObservable;
			if (wordMask.isWordRevealed() == true) {
				updateRevealedWordMessage(wordMask.getMaskedWord(), wordMask
						.getProberb());
			} else if (wordMask.isHung() == true) {
				consoleTextArea.append(resourceBundle.getString(HUNG_MSG) + "\n");
			} else {
				int numberOfRevealedLetters = wordMask
						.getNumberOfRevealedLetters();
				char revealedLetter = wordMask.getLastRevealedLetter();
				updateCurrentWordMessage(numberOfRevealedLetters,
						revealedLetter);
			}
		} else {
			consoleTextArea.append(resourceBundle.getString(ERROR_MSG) + "\n");
		}
	}

	private void updateCurrentWordMessage(int numberOfRevealedLetters,
			char revealedLetter) {
		String currentMessage = "";
		if (numberOfRevealedLetters > 0) {
			currentMessage = resourceBundle.getString(REVEAL_LETTER_MSG) + " "
					+ numberOfRevealedLetters + " "
					+ resourceBundle.getString(LETTERS_MSG) + " '"
					+ revealedLetter + "'";
		} else {
			currentMessage = resourceBundle.getString(NO_REVEALED_LETTERS_MSG)
					+ " '" + revealedLetter + "'";
		}
		currentMessage += '\n';
		consoleTextArea.append(currentMessage);
//		repaint();
	}

	private void updateRevealedWordMessage(String revealedWord, String proverb) {
		consoleTextArea.append(resourceBundle.getString(WORD_REVEALED_MSG)
				+ "\n" + revealedWord + "\n");
		// updateCurrentWordMessage(revealedWord);
		consoleTextArea.append(resourceBundle.getString(PROVERB_MSG) + "\n");
		consoleTextArea.append(proverb + "\n");
	}
}
