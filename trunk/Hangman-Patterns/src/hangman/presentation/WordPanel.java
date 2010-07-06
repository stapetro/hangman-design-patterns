package hangman.presentation;

import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.languages.LanguageResourcesFactory;
import hangman.logic.WordMask;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Observer {

	private static final String ALPHABET_STR = "alphabet";
	private static final String CURRENT_WORD_MSG = "currentWordMessage";
	private static final String CURRENT_CATEGORY_MSG = "currentCategoryMessage";
	private static final String COMMA = ",";

	private ResourceBundle resourceBundle;

	private static final long serialVersionUID = 1L;
	private static final Font STANDARD_FONT = new Font("Serif", Font.PLAIN, 14);
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

		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		this.setLayout(gridBagLayout);
		this.setSize(100, 200);
		this.categoryWordLabel = new JLabel();
		categoryWordLabel.setFont(STANDARD_FONT);
		this.categoryWordLabel.setText(resourceBundle
				.getString(CURRENT_CATEGORY_MSG)
				+ " : ");

		this.categoryWordValueLabel = new JLabel();
		this.categoryWordValueLabel.setFont(STANDARD_FONT);

		this.secretWordLabel = new JLabel();
		this.secretWordLabel.setFont(STANDARD_FONT);

		this.wordMessageLabel = new JLabel();
		wordMessageLabel.setFont(STANDARD_FONT);
		wordMessageLabel.setText(resourceBundle.getString(CURRENT_WORD_MSG)
				+ " : ");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(categoryWordLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		this.add(categoryWordValueLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		// c.weightx = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		this.add(wordMessageLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		// c.weightx = 0.1;
		c.gridx = 1;
		c.gridy = 1;
		this.add(secretWordLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		this.add(getAlphabetPanel(), c);
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
		updateUI();
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
				this.categoryWordValueLabel.setText(resourceBundle.getString(desc));
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
			//refreshMaskedWord();

			JButton sourceButton = (JButton) event.getSource();
			sourceButton.setEnabled(false);
		}
	}

	@Override
	public void update(Observable wordMaskObservable, Object arg) {
		refreshMaskedWord();
	}

}
