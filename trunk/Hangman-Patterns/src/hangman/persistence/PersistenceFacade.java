package hangman.persistence;

import hangman.constants.CategoryItemProperty;
import hangman.constants.HangmanConstants;
import hangman.constants.LanguageItemProperty;
import hangman.constants.LevelItemProperty;
import hangman.domain.Player;
import hangman.domain.ScoreBoard;
import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.domain.config.LevelItem;
import hangman.persistence.config.ConfigurationItemParrserFactory;
import hangman.persistence.config.ConfigurationParser;
import hangman.persistence.config.IConfigurationItemParser;
import hangman.persistence.config.SettingsParser;
import hangman.persistence.scoreboard.ScoreBoardManager;
import hangman.persistence.words.WordParser;

import java.util.List;

//TODO methods should be implemented
public class PersistenceFacade implements IPersistenceFacade {

	private ConfigurationParser configurationParser;
	private IConfigurationItemParser langConfigParser;
	private IConfigurationItemParser levelConfigParser;
	private IConfigurationItemParser categoryParser;
	private SettingsParser settingsParser;
	private WordParser wordParser;
	private ScoreBoardManager scoreBoardMgr;

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
		this.scoreBoardMgr = new ScoreBoardManager(this.configurationParser,
				this.settingsParser);
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

	@Override
	public ScoreBoard getScoreBoardByLevel(int levelId) {
		return this.scoreBoardMgr.getScoreBoardByLevelId(levelId);
	}

	@Override
	public ScoreBoard getCurrentScoreBoard() {
		return this.scoreBoardMgr.getCurrentScoreBoard();
	}

	@Override
	public void addPlayer(Player player) {
		Integer levelId = this.settingsParser.getCurrentLevelId();
		if (levelId == null) {
			levelId = HangmanConstants.DEFAULT_LEVEL_ID;
		}
		addPlayer(player, levelId);
	}

	@Override
	public void addPlayer(Player player, int levelId) {
		String scoreBoardXmlFilePath = this.configurationParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SCORE_BOARD);
		if (scoreBoardXmlFilePath != null && scoreBoardXmlFilePath.length() > 0) {
			this.scoreBoardMgr
					.addPlayer(player, levelId, scoreBoardXmlFilePath);
		}
	}

}
