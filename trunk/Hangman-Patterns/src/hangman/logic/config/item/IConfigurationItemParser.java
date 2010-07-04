package hangman.logic.config.item;

import hangman.domain.config.ConfigurationItem;

import java.util.List;

public interface IConfigurationItemParser {

	public ConfigurationItem getConfigurationItemById(int id);

	public List<ConfigurationItem> getConfigurationItems();

}
