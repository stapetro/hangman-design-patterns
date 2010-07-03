package hangman.persistence;

import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageConfigurationItem;

import java.util.List;

/**
 * Persistence facade for retrieving and storing persistence configuration data.
 * 
 */
public interface IPersistenceFacade {

	public List<ConfigurationItem> getCategories();

	public void addCategory(ConfigurationItem categoryItem);

	public void removeCategory(int categoryId);

	public List<LanguageConfigurationItem> getLanguages();

	public void addLanguage(LanguageConfigurationItem languageItem);

	public void removeLanguage(int languageId);

	public LanguageConfigurationItem getCurrentLanguage();

	// getCurrentLevel()

}
