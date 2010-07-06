package hangman.presentation;

import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.languages.LanguageResourcesFactory;
import hangman.logic.WordMask;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WordPanel extends JPanel {

	private static final String ALPHABET_STR = "alphabet";
	private static final String CURRENT_WORD_MSG = "currentWordMessage";
	private static final String CURRENT_CATEGORY_MSG = "currentCategoryMessage";
	private static final String COMMA = ",";

	private ResourceBundle resourceBundle;

	private static final long serialVersionUID = 1L;
	private static final Font STANDARD_FONT = new Font("Serif", Font.PLAIN, 20);
	private JPanel alphabetPanel = null;
	private JLabel categoryWordLabel = null;
	private JLabel categoryWordValueLabel = null;
	private JLabel secretWordLabel = null;
	private JLabel wordMessageLabel = null;

	private WordMask wordMask; // @jve:decl-index=0:

	/**
	 * This is the default constructor
	 */
	public WordPanel(WordMask wordMaskObj) {
		super();
		initialize(wordMaskObj);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(WordMask wordMaskObj) {
		resourceBundle = LanguageResourcesFactory.getLanguageResource();
		this.wordMask = wordMaskObj;

		secretWordLabel = new JLabel();
		secretWordLabel.setFont(STANDARD_FONT);
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getAlphabetPanel(), BorderLayout.SOUTH);
		this.add(secretWordLabel, BorderLayout.CENTER);
		wordMessageLabel = new JLabel();
		this.categoryWordLabel = new JLabel();
		this.categoryWordLabel.setFont(STANDARD_FONT);
		this.categoryWordValueLabel = new JLabel();
		this.categoryWordValueLabel.setFont(STANDARD_FONT);
		this.add(categoryWordLabel, BorderLayout.NORTH);
		this.add(categoryWordValueLabel, BorderLayout.NORTH);
		this.add(wordMessageLabel, BorderLayout.WEST);
		wordMessageLabel.setText(resourceBundle.getString(CURRENT_WORD_MSG)
				+ " : ");
		wordMessageLabel.setFont(STANDARD_FONT);
		this.categoryWordLabel.setText(resourceBundle
				.getString(CURRENT_CATEGORY_MSG)
				+ " : ");
		setCategoryValueLabel();
		refreshMaskedWord();
		populateAlphabetLetters(alphabetPanel);
	}

	private void populateAlphabetLetters(JPanel alphabetPnl) {
		String alphabet = resourceBundle.getString(ALPHABET_STR);
		String[] alphabetChars = alphabet.split(COMMA);

		for (int i = 0; i < alphabetChars.length; i++) {
			JButton letterBtn = new JButton(alphabetChars[i]);
			LetterButtonHangler letterBtnHangler = new LetterButtonHangler();
			letterBtn.addActionListener(letterBtnHangler);
			alphabetPnl.add(letterBtn);
		}
	}

	/**
	 * Refreshes the value of the masked word in the panel and rewrites it
	 */
	private void refreshMaskedWord() {
		secretWordLabel.setText(wordMask.getMaskedWord());
	}

	/**
	 * This method initializes alphabetPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAlphabetPanel() {
		if (alphabetPanel == null) {
			alphabetPanel = new JPanel();
			alphabetPanel.setLayout(new GridLayout(3, 10));
		}
		return alphabetPanel;
	}

	private void setCategoryValueLabel() {
		WordItem wordItem = this.wordMask.getWordItem();
		if (wordItem != null) {
			ConfigurationItem category = wordItem.getCategory();
			if (category != null) {
				String desc = category.getDescription();
				if (desc == null) {
					desc = "";
				}
				System.out.println(desc);
				this.categoryWordValueLabel.setText(desc);
			}
		}
	}

	/**
	 * class handlink button clicks.
	 * 
	 */
	class LetterButtonHangler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String selectedLetter = event.getActionCommand();
			wordMask.revealLetter(selectedLetter.charAt(0));
			refreshMaskedWord();

			JButton sourceButton = (JButton) event.getSource();
			sourceButton.setEnabled(false);
		}
	}

}
