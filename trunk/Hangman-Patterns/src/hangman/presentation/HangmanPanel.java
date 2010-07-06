package hangman.presentation;

import hangman.languages.LanguageResourcesFactory;
import hangman.logic.WordMask;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HangmanPanel extends JPanel implements Observer {

	private static final String MISTAKES_MSG = "mistakesMsg";
	private static final Font STANDARD_FONT = new Font("Serif", Font.PLAIN, 14);

	private static final long serialVersionUID = 1L;

	private ResourceBundle resourceBundle;

	private JLabel mistakesMsgLabel;
	private JLabel numberOfMistakesLabel;
	private ImagePanel imagePanel;

	/**
	 * This is the default constructor
	 */
	public HangmanPanel() {
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
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		this.mistakesMsgLabel = new JLabel();
		this.mistakesMsgLabel.setFont(STANDARD_FONT);
		this.mistakesMsgLabel.setText(resourceBundle.getString(MISTAKES_MSG));

		this.numberOfMistakesLabel = new JLabel();
		this.numberOfMistakesLabel.setFont(STANDARD_FONT);

		this.imagePanel = new ImagePanel();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(this.imagePanel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		this.add(this.mistakesMsgLabel, c);
		c.fill = GridBagConstraints.RELATIVE;
		// c.gridx = 1;
		// c.gridy = 1;
		this.add(this.numberOfMistakesLabel, c);
	}

	@Override
	public void update(Observable wordMaskObservable, Object arg) {
		if (wordMaskObservable instanceof WordMask) {
			WordMask wordMask = (WordMask) wordMaskObservable;
			if (wordMask != null) {
				if (wordMask.isHung()) {
					this.imagePanel.drawImage();
					this.imagePanel.repaint();
				} else {
					int currentTotalMistakes = wordMask.getTotalMistakes();
					int maxMisaktes = wordMask.getMaxNumberOfMistakes();
					this.numberOfMistakesLabel.setText(currentTotalMistakes
							+ "/" + maxMisaktes);
					this.imagePanel.setVisibleImage(false);
					this.imagePanel.repaint();
				}
			}
		}
	}

}
