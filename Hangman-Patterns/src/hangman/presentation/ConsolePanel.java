package hangman.presentation;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

public class ConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane consoleScrollPane = null;
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

}
