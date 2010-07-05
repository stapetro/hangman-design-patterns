package hangman.persistence.config;

import hangman.domain.config.ConfigurationItem;

import java.util.List;

/**
 * Look like Adapter (Wrapper) pattern. Wraps XML manager interface.
 * 
 */
public interface IConfigurationItemParser {

	public ConfigurationItem getConfigurationItemById(int id);

	public List<ConfigurationItem> getConfigurationItems();

}
