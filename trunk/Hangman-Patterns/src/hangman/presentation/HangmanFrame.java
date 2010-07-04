package hangman.presentation;

import hangman.languages.LanguageResourcesFactory;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This is the main JFrame container for Hangman.
 */
public class HangmanFrame extends JFrame {

	private static final String HANGMAN_STR = "hangman";
	private static final String GAME_STR = "game";
	private static final String NEW_GAME_STR = "newGame";
	private static final String SAVE_GAME_STR = "saveGame";
	private static final String EXIT_STR = "exit";
	private static final String GAME_OPTIONS_STR = "gameOptions";
	private static final String REVEAL_LETTER_STR = "revealLetter";  //  @jve:decl-index=0:
	private static final String REVEAL_WORD_STR = "revealWord";

	/**
	 * Resource Bundle object for internationalization
	 */
	private ResourceBundle resourceBundle;  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar menu = null;
	private JMenu gameMenu = null;
	private JMenuItem newGameMenuItem = null;
	private JMenuItem saveGameMenuItem = null;
	private JMenuItem exitMenuItem = null;
	private JMenu gameOptionsMenu = null;
	private JMenuItem revealNextLetterMenuItem = null;
	private JMenuItem revealWordMenuItem = null;
	
	private HangmanPanel hangmanPanel;
	private ConsolePanel consolePanel;

	/**
	 * This is the default constructor
	 */
	public HangmanFrame() {
		super();
		initialize();
		initializeComponents();
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
	}

	/**
	 * Initialize components that need not be refreshed when languages are
	 * changed.
	 */
	private void initializeComponents() {
		//add the hangman panel for hanging users
		hangmanPanel = new HangmanPanel();
		jContentPane.add(hangmanPanel, BorderLayout.WEST);
		
		//add the console panel
		consolePanel = new ConsolePanel();
		jContentPane.add(consolePanel, BorderLayout.EAST);
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
			gameMenu.add(getNewGameMenuItem());
			gameMenu.add(getSaveGameMenuItem());
			gameMenu.add(getExitMenuItem());
		}
		return gameMenu;
	}

	/**
	 * This method initializes newGameMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getNewGameMenuItem() {
		if (newGameMenuItem == null) {
			newGameMenuItem = new JMenuItem();
			newGameMenuItem.setText(resourceBundle.getString(NEW_GAME_STR));
		}
		return newGameMenuItem;
	}

	/**
	 * This method initializes saveGameMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getSaveGameMenuItem() {
		if (saveGameMenuItem == null) {
			saveGameMenuItem = new JMenuItem();
			saveGameMenuItem.setText(resourceBundle.getString(SAVE_GAME_STR));
		}
		return saveGameMenuItem;
	}

	/**
	 * This method initializes exitMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText(resourceBundle.getString(EXIT_STR));
		}
		return exitMenuItem;
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
			gameOptionsMenu.add(getRevealNextLetterMenuItem());
			gameOptionsMenu.add(getRevealWordMenuItem());
		}
		return gameOptionsMenu;
	}

	/**
	 * This method initializes revealNextLetterMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getRevealNextLetterMenuItem() {
		if (revealNextLetterMenuItem == null) {
			revealNextLetterMenuItem = new JMenuItem();
			revealNextLetterMenuItem.setText(resourceBundle
					.getString(REVEAL_LETTER_STR));
		}
		return revealNextLetterMenuItem;
	}

	/**
	 * This method initializes revealWordMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getRevealWordMenuItem() {
		if (revealWordMenuItem == null) {
			revealWordMenuItem = new JMenuItem();
			revealWordMenuItem.setText(resourceBundle
					.getString(REVEAL_WORD_STR));
		}
		return revealWordMenuItem;
	}

}
