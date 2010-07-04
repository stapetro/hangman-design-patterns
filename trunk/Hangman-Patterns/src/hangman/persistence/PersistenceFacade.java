package hangman.persistence;

import hangman.constants.CategoryItemProperty;
import hangman.constants.HangmanConstants;
import hangman.constants.LanguageItemProperty;
import hangman.constants.LevelItemProperty;
import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.domain.config.LevelItem;
import hangman.logic.config.ConfigurationParser;
import hangman.logic.config.SettingsParser;
import hangman.logic.config.WordParser;
import hangman.logic.config.item.ConfigurationItemParrserFactory;
import hangman.logic.config.item.IConfigurationItemParser;

import java.util.List;

//TODO methods should be implemented
public class PersistenceFacade implements IPersistenceFacade {

	private ConfigurationParser configurationParser;
	private IConfigurationItemParser langConfigParser;
	private IConfigurationItemParser levelConfigParser;
	private IConfigurationItemParser categoryParser;
	private SettingsParser settingsParser;
	private WordParser wordParser;

	public PersistenceFacade() {
		this.configurationParser = new ConfigurationParser();
		this.langConfigParser = ConfigurationItemParrserFactory
				.newConfigurationItemParser(this.configurationParser,
						LanguageItemProperty.ID);
		this.levelConfigParser = ConfigurationItemParrserFactory
				.newConfigurationItemParser(this.configurationParser,
						LevelItemProperty.ID);
		this.categoryParser = ConfigurationItemParrserFactory
				.newConfigurationItemParser(this.configurationParser,
						CategoryItemProperty.ID);
		this.settingsParser = new SettingsParser(this.configurationParser);
		this.wordParser = new WordParser(this.configurationParser,
				this.langConfigParser, this.categoryParser);
	}

	@Override
	public void addCategory(ConfigurationItem categoryItem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addLanguage(LanguageItem languageItem) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ConfigurationItem> getCategories() {
		return this.categoryParser.getConfigurationItems();
	}

	@Override
	public LanguageItem getCurrentLanguage() {
		Integer langId = this.settingsParser.getCurrentLanguageId();
		if (langId != null) {
			ConfigurationItem configItem = this.langConfigParser
					.getConfigurationItemById(langId);
			if (configItem != null && configItem instanceof LanguageItem) {
				return (LanguageItem) configItem;
			}
		}
		return null;
	}

	@Override
	public List<ConfigurationItem> getLanguages() {
		return this.langConfigParser.getConfigurationItems();
	}

	@Override
	public void removeCategory(int categoryId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeLanguage(int languageId) {
		// TODO Auto-generated method stub

	}

	@Override
	public LevelItem getCurrentLevel() {
		Integer levelId = this.settingsParser.getCurrentLevelId();
		if (levelId != null) {
			ConfigurationItem configItem = this.levelConfigParser
					.getConfigurationItemById(levelId);
			if (configItem != null && configItem instanceof LevelItem) {
				return (LevelItem) configItem;
			}
		}
		return null;
	}

	@Override
	public int getCurrentScoreBoardSize() {
		Integer scoreBoardSize = this.settingsParser.getCurrentScoreBoardSize();
		if (scoreBoardSize != null) {
			return scoreBoardSize;
		} else {
			return HangmanConstants.DEFAULT_SCORE_BOARD_SIZE;
		}
	}

	@Override
	public List<ConfigurationItem> getLevels() {
		return this.levelConfigParser.getConfigurationItems();
	}

	@Override
	public List<WordItem> getWordsByLanguage(int langaugeId) {
		return this.wordParser.getWordsByLanguage(langaugeId);
	}

}
