package hangman.domain.config;

import java.util.HashMap;

import hangman.constants.CategoryItemProperty;
import hangman.constants.IConfigurationItemProperty;
import hangman.constants.LanguageItemProperty;
import hangman.constants.LevelItemProperty;
import hangman.constants.WordItemProperty;

/**
 * Factory pattern.
 * 
 */
public class ConfigurationItemFactory {

	public static ConfigurationItem createConfigurationItem(
			IConfigurationItemProperty configItemProperty,
			HashMap<String, String> configItemProperties) {
		if (configItemProperty instanceof LanguageItemProperty) {
			return new LanguageItem(configItemProperties);
		}
		if (configItemProperty instanceof LevelItemProperty) {
			return new LevelItem(configItemProperties);
		}
		if (configItemProperty instanceof CategoryItemProperty) {
			return new CategoryItem(configItemProperties);
		}
		if (configItemProperty instanceof WordItemProperty) {
			return null;
		}
		return null;
	}

}
