package hangman.presentation;

import hangman.domain.GameState;
import hangman.languages.LanguageResourcesFactory;
import hangman.logic.WordMask;
import hangman.logic.WordMask.HangmanMemento;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class LoadGameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String LOAD_GAME_STR = "loadGame";

	private ResourceBundle resourceBundle;

	private JPanel jContentPane = null;
	private JList savedGamesList = null;
	private String[] savedGamesDescriptions = null;
	private List<GameState> gameStatesList = null;
	private WordMask wordMask = null;
	private JButton loadGameButton = null;

	/**
	 * General purpose constructor
	 * 
	 * @param inputGameStatesList
	 *            list of saved gamestates
	 * @param inputWordMask
	 *            wordMask to update
	 */
	public LoadGameFrame(List<GameState> inputGameStatesList,
			WordMask inputWordMask) {
		super();
		this.gameStatesList = inputGameStatesList;
		this.wordMask = inputWordMask;
		setSavedGamesDescriptions(inputGameStatesList);
		initialize();
	}

	/**
	 * This is the default constructor
	 */
	public LoadGameFrame() {
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
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getSavedGamesList(), BorderLayout.CENTER);
			jContentPane.add(getLoadGameButton(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes savedGamesList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getSavedGamesList() {
		if (savedGamesList == null) {
			savedGamesList = new JList(savedGamesDescriptions);
		}
		return savedGamesList;
	}

	/**
	 * update the list of game state descriptionsF
	 * 
	 * @param gameStatesList
	 */
	private void setSavedGamesDescriptions(List<GameState> gameStatesList) {
		savedGamesDescriptions = new String[gameStatesList.size()];

		for (int i = 0; i < gameStatesList.size(); i++) {
			GameState gameState = gameStatesList.get(i);
			savedGamesDescriptions[i] = gameState.getDescription();
		}
	}

	/**
	 * This method initializes loadGameButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getLoadGameButton() {
		if (loadGameButton == null) {
			loadGameButton = new JButton(resourceBundle
					.getString(LOAD_GAME_STR));
			loadGameButton.addActionListener(new SaveGameHandler());
		}
		return loadGameButton;
	}

	private class SaveGameHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIndex = savedGamesList.getSelectedIndex();
			GameState gameState = gameStatesList.get(selectedIndex);
			HangmanMemento memento = gameState.getGameStateMemento();
			wordMask.restoreFromMemento(memento);
			dispose();
		}
	}

}
