package hangman.presentation;

import hangman.languages.LanguageResourcesFactory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel {
	
	private static final String ALPHABET_STR = "alphabet";
	private static final String COMMA = ",";

	private ResourceBundle resourceBundle;  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JPanel alphabetPanel = null;

	/**
	 * This is the default constructor
	 */
	public WordPanel() {
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

		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getAlphabetPanel(), BorderLayout.SOUTH);
		populateAlphabetLetters(alphabetPanel);
	}
	
	private void populateAlphabetLetters(JPanel alphabetPnl){
		String alphabet = resourceBundle.getString(ALPHABET_STR);
		String[] alphabetChars = alphabet.split(COMMA);
		
		for(int i = 0; i < alphabetChars.length; i++){
			JButton letterBtn = new JButton(alphabetChars[i]);
			alphabetPnl.add(letterBtn);
		}
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

}
