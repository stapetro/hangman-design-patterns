package hangman.persistence;

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

	public List<LanguageItem> getLanguages();
	
	public LanguageItem getCurrentLanguage();	

	public void addLanguage(LanguageItem languageItem);

	public void removeLanguage(int languageId);
	
	public List<LevelItem> getLevels();
	
	public LevelItem getCurrentLevel();
	
	public int getCurrentScoreBoardSize();
	
	public List<WordItem> getWordsByLanguage(int langaugeId);

}
