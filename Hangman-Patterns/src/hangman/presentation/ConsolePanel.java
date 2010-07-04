package hangman.presentation;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class ConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;

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
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
	}

}
