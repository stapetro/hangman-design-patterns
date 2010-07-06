package hangman.presentation;

import hangman.logic.WordMask;

import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class HangmanPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
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
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
	}

	@Override
	public void update(Observable wordMaskObservable, Object arg) {
		System.out.println("test....");
		if(wordMaskObservable instanceof WordMask) {
			WordMask wordMask = (WordMask)wordMaskObservable;
			if(wordMask != null) {
				if(wordMask.isHung()) {
					System.out.println("hug....");
				} else {
					int currentTotalMistakes = wordMask.getTotalMistakes();
					int maxMisaktes = wordMask.getMaxNumberOfMistakes();
					System.out.println(currentTotalMistakes + "/" + maxMisaktes);
				}
			}
		}
	}

}
