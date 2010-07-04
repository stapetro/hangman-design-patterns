package hangman.logic.config.item;

import hangman.constants.CategoryItemProperty;
import hangman.constants.IConfigurationItemProperty;
import hangman.constants.LanguageItemProperty;
import hangman.constants.LevelItemProperty;
import hangman.logic.config.ConfigurationParser;

/**
 * Factory for creating configuration item parsers.
 *
 */
public class ConfigurationItemParrserFactory {

	public static IConfigurationItemParser newConfigurationItemParser(
			ConfigurationParser configParser,
			IConfigurationItemProperty configItemType) {
		if(configItemType instanceof LanguageItemProperty) {
			return new LanguageItemParser(configParser);
		}
		if(configItemType instanceof LevelItemProperty) {
			return new LevelItemParser(configParser);
		}
		if(configItemType instanceof CategoryItemProperty) {
			return new CategoryItemParser(configParser);
		}
		return null;
	}

}
