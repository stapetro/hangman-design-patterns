package hangman.presentation;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;

public class ConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea consoleTextArea = null;

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
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		this.setSize(203, 258);
		this.setLayout(new GridBagLayout());
		this.add(getConsoleTextArea(), gridBagConstraints);
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

}
