package hangman.persistence.config;

import hangman.constants.CategoryItemProperty;
import hangman.constants.HangmanConstants;
import hangman.constants.IConfigurationItemProperty;
import hangman.constants.LanguageItemProperty;
import hangman.constants.LevelItemProperty;

/**
 * Factory for creating configuration item parsers.
 * 
 */
public class ConfigurationItemParrserFactory {

	public static IConfigurationItemParser newConfigurationItemParser(
			ConfigurationParser configParser,
			IConfigurationItemProperty configItemType) {
		if (configItemType instanceof LanguageItemProperty) {
			return new LanguageItemParser(
					configParser
							.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_LANGUAGE));
		}
		if (configItemType instanceof LevelItemProperty) {
			return new LevelItemParser(
					configParser
							.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_LEVELS));
		}
		if (configItemType instanceof CategoryItemProperty) {
			return new CategoryItemParser(
					configParser
							.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_CATEGORIES));
		}
		return null;
	}

}
