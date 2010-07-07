package hangman.presentation;

import hangman.domain.GameState;
import hangman.domain.ScoreBoard;
import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.languages.LanguageResourcesFactory;
import hangman.logic.WordGenerator;
import hangman.logic.WordMask;
import hangman.logic.WordMask.HangmanMemento;
import hangman.persistence.IPersistenceFacade;
import hangman.persistence.PersistenceFacadeSingleton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the main JFrame container for Hangman.
 */
public class HangmanFrame extends JFrame {

	private static final String HANGMAN_STR = "hangman";
	private static final String GAME_STR = "game";
	private static final String NEW_GAME_STR = "newGame"; // @jve:decl-index=0:
	private static final String SAVE_GAME_STR = "saveGame";
	private static final String LOAD_GAME_STR = "loadGame"; // @jve:decl-index=0:
	private static final String EXIT_STR = "exit"; // @jve:decl-index=0:
	private static final String GAME_OPTIONS_STR = "gameOptions";
	private static final String REVEAL_LETTER_STR = "revealLetter";
	private static final String REVEAL_WORD_STR = "revealWord";
	private static final String ENTER_GAME_DESCRIPTION_MSG = "enterGameDescription"; // @jve:decl-index=0:
	private static final String LANGUAGE_STR = "languageString";
	private static final String SCOREBOARD_STR = "scoreboard";

	/**
	 * Resource Bundle object for internationalization
	 */
	private ResourceBundle resourceBundle; // @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar menu = null;
	private JMenu gameMenu = null;
	private JMenu gameOptionsMenu = null;
	private JMenu languageMenu = null;

	private HangmanPanel hangmanPanel;
	private ConsolePanel consolePanel;
	private WordPanel wordsPanel;

	/**
	 * Object for working with the work mask
	 */
	private WordMask wordMask; // @jve:decl-index=0:

	/**
	 * This is the default constructor
	 */
	public HangmanFrame() {
		super();
		initialize();
		initializeComponents();
		initializeObservers();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		resourceBundle = LanguageResourcesFactory.getLanguageResource();

		this.setSize(652, 452);
		this.setJMenuBar(getMenu());
		this.setContentPane(getJContentPane());
		this.setTitle(resourceBundle.getString(HANGMAN_STR));

		wordMask = initializeWordMask();
		initializeWordPanel(wordMask);
	}

	/**
	 * initialize the wordPanel
	 * 
	 * @param wordMaskObj
	 *            the WordMask object the the panel will be working with
	 */
	private void initializeWordPanel(WordMask wordMaskObj) {
		wordsPanel = new WordPanel(wordMaskObj);
		jContentPane.add(wordsPanel, BorderLayout.SOUTH);
	}

	/**
	 * Initialize components that need not be refreshed when languages are
	 * changed.
	 */
	private void initializeComponents() {
		// add the hangman panel for hanging users
		hangmanPanel = new HangmanPanel();
		jContentPane.add(hangmanPanel, BorderLayout.WEST);

		// add the console panel
		consolePanel = new ConsolePanel();
		jContentPane.add(consolePanel, BorderLayout.EAST);
	}

	public void initializeObservers() {
		wordMask.addObserver(consolePanel);
		wordMask.addObserver(wordsPanel);
		wordMask.addObserver(hangmanPanel);
	}

	/**
	 * This method initializes the word mask object
	 * 
	 * @return the word mask
	 */
	private WordMask initializeWordMask() {
		WordItem word = WordGenerator.getRandomWordItem();
		if (wordMask == null) {
			wordMask = new WordMask(word);
		} else {
			wordMask.setWordItem(word);
		}
		return wordMask;
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
		}
		return jContentPane;
	}

	/**
	 * This method initializes menu
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getMenu() {
		if (menu == null) {
			menu = new JMenuBar();
			menu.add(getGameMenu());
			menu.add(getGameOptionsMenu());
			menu.add(getLanguageMenu());
		}
		return menu;
	}

	/**
	 * This method initializes gameMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getGameMenu() {
		if (gameMenu == null) {
			gameMenu = new JMenu();
			gameMenu.setText(resourceBundle.getString(GAME_STR));
			gameMenu.add(new NewGameAction());
			gameMenu.add(new SaveGameAction());
			gameMenu.add(new LoadGameAction());
			gameMenu.add(new ExitGameAction());
		}
		return gameMenu;
	}

	/**
	 * This method initializes gameOptionsMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getGameOptionsMenu() {
		if (gameOptionsMenu == null) {
			gameOptionsMenu = new JMenu();
			gameOptionsMenu.setText(resourceBundle.getString(GAME_OPTIONS_STR));
			gameOptionsMenu.add(new RevealLetterAction());
			gameOptionsMenu.add(new RevealWordAction());
			gameOptionsMenu.add(new ScoreBoardItemAction());
		}
		return gameOptionsMenu;
	}

	/**
	 * This method initializes languageMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getLanguageMenu() {
		if (languageMenu == null) {
			languageMenu = new JMenu();
			languageMenu.setText(resourceBundle.getString(LANGUAGE_STR));

			List<ConfigurationItem> languages = getAvailableLanguages();
			for (ConfigurationItem item : languages) {
				int languageId = item.getId();
				String languageName = item.getDescription();
				languageMenu.add(new LanguageItemAction(languageId,
						languageName));
			}
		}
		return languageMenu;
	}

	private List<ConfigurationItem> getAvailableLanguages() {
		IPersistenceFacade facade = PersistenceFacadeSingleton.getInstance();
		List<ConfigurationItem> configItems = facade.getLanguages();

		return configItems;
	}

	/**
	 * Command for new game menu item
	 */
	private class NewGameAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public NewGameAction() {
			super(resourceBundle.getString(NEW_GAME_STR));
		}

		public void actionPerformed(ActionEvent e) {
			initializeWordMask();
//			jContentPane.remove(wordsPanel);
//			initializeWordPanel(wordMask);
			wordsPanel = new WordPanel(wordMask);
			wordsPanel.update(wordMask, null);
		}
	}

	/**
	 * Command for save game menu item
	 */
	private class SaveGameAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveGameAction() {
			super(resourceBundle.getString(SAVE_GAME_STR));
		}

		public void actionPerformed(ActionEvent e) {
			IPersistenceFacade facade = PersistenceFacadeSingleton
					.getInstance();
			HangmanMemento savedGameState = wordMask.saveToMemento();

			String hintMessage = resourceBundle
					.getString(ENTER_GAME_DESCRIPTION_MSG);
			String description = JOptionPane.showInputDialog(hintMessage);

			if (description != null && description.trim().length() > 0) {
				GameState gameState = new GameState(savedGameState, description);
				facade.saveGameState(gameState);
			}
		}
	}

	/**
	 * Command for loading game
	 */
	private class LoadGameAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public LoadGameAction() {
			super(resourceBundle.getString(LOAD_GAME_STR));
		}

		public void actionPerformed(ActionEvent e) {
			IPersistenceFacade facade = PersistenceFacadeSingleton
					.getInstance();
			List<GameState> gameStatesList = facade.getSavedGameStates();
			LoadGameFrame loadFrame = new LoadGameFrame(gameStatesList,
					wordMask);
			loadFrame.setSize(400, 400);
			loadFrame.setVisible(true);
		}
	}

	/**
	 * Command for exit game action
	 */
	private class ExitGameAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ExitGameAction() {
			super(resourceBundle.getString(EXIT_STR));
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(NORMAL);
		}
	}

	/**
	 * Command for reveal next letter
	 */
	private class RevealLetterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public RevealLetterAction() {
			super(resourceBundle.getString(REVEAL_LETTER_STR));
		}

		public void actionPerformed(ActionEvent e) {
			wordMask.revealLetter();
			wordsPanel.update(wordMask, null);
		}
	}

	/**
	 * Command for levealing the whole letter
	 */
	private class RevealWordAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public RevealWordAction() {
			super(resourceBundle.getString(REVEAL_WORD_STR));
		}

		public void actionPerformed(ActionEvent e) {
			while (wordMask.isWordRevealed() == false) {
				wordMask.revealLetter();
			}
			wordsPanel.update(wordMask, null);
		}
	}

	private class ScoreBoardItemAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ScoreBoardItemAction() {
			super(resourceBundle.getString(SCOREBOARD_STR));
		}

		public void actionPerformed(ActionEvent e) {
			IPersistenceFacade facade = PersistenceFacadeSingleton
					.getInstance();
			ScoreBoard scoreBoard = facade.getCurrentScoreBoard();
			String stringScoreboard = (scoreBoard == null) ? "" : scoreBoard
					.toString();
			JOptionPane.showMessageDialog(null, stringScoreboard);
		}
	}

	private class LanguageItemAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private int languageId;

		public LanguageItemAction(int id, String language) {
			super(language);
			this.languageId = id;
		}

		public void actionPerformed(ActionEvent e) {
			IPersistenceFacade facade = PersistenceFacadeSingleton
					.getInstance();
			facade.setCurrentLanguage(languageId);

			// jContentPane = null;
			// menu = null;
			// gameMenu = null;
			// gameOptionsMenu = null;
			// languageMenu = null;
			//
			// hangmanPanel = null;
			// consolePanel = null;
			// wordsPanel = null;
			// wordMask = null;
			// initialize();
			// jContentPane.updateUI();
			// wordsPanel.update(wordMask, null);
		}
	}
}
