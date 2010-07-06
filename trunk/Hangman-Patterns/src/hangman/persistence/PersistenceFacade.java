package hangman.persistence;

import hangman.constants.CategoryItemProperty;
import hangman.constants.HangmanConstants;
import hangman.constants.LanguageItemProperty;
import hangman.constants.LevelItemProperty;
import hangman.domain.GameState;
import hangman.domain.ScoreBoard;
import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.domain.config.LevelItem;
import hangman.persistence.config.ConfigurationItemParrserFactory;
import hangman.persistence.config.ConfigurationParser;
import hangman.persistence.config.IConfigurationItemParser;
import hangman.persistence.config.SettingsPersister;
import hangman.persistence.gamestate.GameStatePersister;
import hangman.persistence.scoreboard.ScoreBoardPersister;
import hangman.persistence.words.WordParser;

import java.util.List;

//TODO methods should be implemented
public class PersistenceFacade implements IPersistenceFacade {

	private ConfigurationParser configurationParser;
	private IConfigurationItemParser langConfigParser;
	private IConfigurationItemParser levelConfigParser;
	private IConfigurationItemParser categoryParser;
	private SettingsPersister settingsPersister;
	private ScoreBoardPersister scoreBoardPersister;
	private GameStatePersister gameStatePersister;
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

		String settingsXmlFilePath = this.configurationParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SETTINGS);
		this.settingsPersister = new SettingsPersister(settingsXmlFilePath);

		String wordXmlFilePath = this.configurationParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_WORDS);
		this.wordParser = new WordParser(wordXmlFilePath,
				this.langConfigParser, this.categoryParser);

		String scoreBoardXmlFilePath = this.configurationParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SCORE_BOARD);
		this.scoreBoardPersister = new ScoreBoardPersister(
				scoreBoardXmlFilePath, this.settingsPersister);

		String gameStateXmlFilePath = this.configurationParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_GAME_STATE);
		this.gameStatePersister = new GameStatePersister(gameStateXmlFilePath);
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
		Integer langId = this.settingsPersister.getCurrentLanguageId();
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
		Integer levelId = this.settingsPersister.getCurrentLevelId();
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
		Integer scoreBoardSize = this.settingsPersister
				.getCurrentScoreBoardSize();
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
		return this.scoreBoardPersister.getScoreBoardByLevelId(levelId);
	}

	@Override
	public ScoreBoard getCurrentScoreBoard() {
		return this.scoreBoardPersister.getCurrentScoreBoard();
	}

	@Override
	public List<WordItem> getWords() {
		Integer languageId = this.settingsPersister.getCurrentLanguageId();
		if (languageId != null) {
			return getWordsByLanguage(languageId);
		}

		return null;
	}

	@Override
	public void addScoreBoard(ScoreBoard scoreBoard) {
		// String scoreBoardXmlFilePath = this.configurationParser
		// .getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SCORE_BOARD);
		// if (scoreBoardXmlFilePath != null && scoreBoardXmlFilePath.length() >
		// 0) {
		Integer levelId = this.settingsPersister.getCurrentLevelId();
		if (levelId == null) {
			levelId = HangmanConstants.DEFAULT_LEVEL_ID;
		}
		this.scoreBoardPersister.addScoreBoard(scoreBoard, levelId);
		// }
	}

	@Override
	public void setCurrentLanguage(int languageId) {
		this.settingsPersister.setCurrentLanguageId(languageId);

	}

	@Override
	public void setCurrentLevel(int levelId) {
		this.settingsPersister.setCurrentLevelId(levelId);
	}

	@Override
	public void setCurrentScoreBoardSize(int scoreBoardSize) {
		this.settingsPersister.setCurrentScoreBoardSize(scoreBoardSize);
	}

	@Override
	public WordItem getWord(int wordId) {
		return this.wordParser.getWord(wordId);
	}

	@Override
	public void saveGameState(GameState gameState) {
		Integer languageId = this.settingsPersister.getCurrentLanguageId();
		if (languageId != null) {
			this.gameStatePersister.saveGameState(gameState, languageId);
		}
	}

	@Override
	public List<GameState> getSavedGameStates() {
		Integer languageId = this.settingsPersister.getCurrentLanguageId();
		if (languageId != null) {
			return this.gameStatePersister.getSavedGameStates(languageId);
		}
		return null;
	}

	@Override
	public LevelItem setCurrentLevelFromWordSize(int wordSize) {
		List<ConfigurationItem> levels = getLevels();
		if (levels != null && levels.size() > 0) {
			LevelItem currentLevel = null;
			for (ConfigurationItem configItem : levels) {
				if (configItem instanceof LevelItem) {
					currentLevel = (LevelItem) configItem;
					int minWordSize = currentLevel.getMinWordLength();
					int maxWordSize = currentLevel.getMaxWordLength();
					if (wordSize >= minWordSize && wordSize <= maxWordSize) {
						setCurrentLevel(currentLevel.getId());
						return currentLevel;
					}
				}
			}
		}
		return getCurrentLevel();
	}
}
