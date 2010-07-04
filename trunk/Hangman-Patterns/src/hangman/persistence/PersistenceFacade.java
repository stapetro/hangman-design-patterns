package hangman.persistence;

import hangman.constants.HangmanConstants;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.domain.config.LevelItem;
import hangman.logic.config.ConfigurationParser;
import hangman.logic.config.LanguageConfigurationParser;
import hangman.logic.config.LevelConfigurationParser;
import hangman.logic.config.SettingsParser;

import java.util.List;

//TODO methods should be implemented
public class PersistenceFacade implements IPersistenceFacade {

	private ConfigurationParser configurationParser;
	private LanguageConfigurationParser langConfigParser;
	private SettingsParser settingsParser;
	private LevelConfigurationParser levelConfigParser;

	public PersistenceFacade() {
		this.configurationParser = new ConfigurationParser();
		this.langConfigParser = new LanguageConfigurationParser(
				this.configurationParser);
		this.settingsParser = new SettingsParser(this.configurationParser);
		this.levelConfigParser = new LevelConfigurationParser(
				this.configurationParser);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LanguageItem getCurrentLanguage() {
		Integer langId = this.settingsParser.getCurrentLanguageId();
		if (langId != null) {
			return this.langConfigParser.getLanguageById(langId);
		}
		return null;
	}

	@Override
	public List<LanguageItem> getLanguages() {
		return this.langConfigParser.getLanguages();
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
			return this.levelConfigParser.getLevelById(levelId);
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
	public List<LevelItem> getLevels() {
		return this.levelConfigParser.getLevels();
	}

}
