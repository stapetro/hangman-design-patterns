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

	private static final long serialVersionUID = 1L;
	private JScrollPane consoleScrollPane = null;
	private JTextArea consoleTextArea = null;

	private ResourceBundle resourceBundle; // @jve:decl-index=0:

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
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.BOTH;
		gridBagConstraints3.gridy = 0;
		gridBagConstraints3.weightx = 1.0;
		gridBagConstraints3.weighty = 1.0;
		gridBagConstraints3.gridx = 0;
		this.setSize(203, 258);
		this.setLayout(new GridBagLayout());
		this.add(getConsoleScrollPane(), gridBagConstraints3);
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
			consoleTextArea.setText("kak si");
		}
		return consoleTextArea;
	}

	/**
	 * Handle the update actions when the WordMask is changed
	 */
	@Override
	public void update(Observable o, Object arg) {
		WordMask wordMask;
		if (o instanceof WordMask) {
			wordMask = (WordMask) o;
			if (wordMask.isWordRevealed() == true) {
				updateRevealedWordMessage(wordMask.getMaskedWord(), wordMask
						.getProberb());
			} else {
				updateCurrentWordMessage(wordMask.getMaskedWord());
			}
		} else {
			consoleTextArea.append(resourceBundle.getString(ERROR_MSG) + "\n");
		}
	}

	private void updateCurrentWordMessage(String maskedWord) {
		consoleTextArea.append(resourceBundle.getString(CURRENT_WORD_MSG)
				+ ":\n");
		consoleTextArea.append(maskedWord + "\n");
	}

	private void updateRevealedWordMessage(String revealedWord, String proverb) {
		consoleTextArea.append(resourceBundle.getString(WORD_REVEALED_MSG)
				+ "\n");
		updateCurrentWordMessage(revealedWord);
		consoleTextArea.append(resourceBundle.getString(PROVERB_MSG) + "\n");
		consoleTextArea.append(proverb + "\n");
	}

}
