package hangman.main;

import hangman.domain.config.LanguageItem;
import hangman.domain.config.LevelItem;
import hangman.persistence.IPersistenceFacade;
import hangman.persistence.PersistenceFacade;

import java.util.List;

public class HangmanStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IPersistenceFacade facade = new PersistenceFacade();
		List<LanguageItem> langs = facade.getLanguages();
		if (langs != null) {
			System.out.println(langs.toString());
		}
		System.out.println("Current lang:\n" + facade.getCurrentLanguage());
		System.out.println("Current score board size:\n"
				+ facade.getCurrentScoreBoardSize());
		List<LevelItem> levels = facade.getLevels();
		if (levels != null) {
			System.out.println(levels.toString());
		}
	}

}
