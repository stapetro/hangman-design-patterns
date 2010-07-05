package hangman.persistence;

import hangman.domain.Player;
import hangman.domain.ScoreBoard;
import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.domain.config.LevelItem;

import java.util.List;

/**
 * Persistence facade for retrieving and storing persistence configuration data.
 * 
 */
public interface IPersistenceFacade {

	public List<ConfigurationItem> getCategories();

	public void addCategory(ConfigurationItem categoryItem);

	public void removeCategory(int categoryId);

	public List<ConfigurationItem> getLanguages();
	
	public LanguageItem getCurrentLanguage();	

	public void addLanguage(LanguageItem languageItem);

	public void removeLanguage(int languageId);
	
	public List<ConfigurationItem> getLevels();
	
	public LevelItem getCurrentLevel();
	
	public int getCurrentScoreBoardSize();
	
	public List<WordItem> getWordsByLanguage(int langaugeId);
	
	public List<WordItem> getWords();
	
	public ScoreBoard getScoreBoardByLevel(int levelId);
	
	public ScoreBoard getCurrentScoreBoard();
	
	public void addPlayer(Player player);
	
	public void addPlayer(Player player, int levelId);

}
