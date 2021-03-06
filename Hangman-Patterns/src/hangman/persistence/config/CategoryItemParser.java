package hangman.persistence.config;

import hangman.constants.CategoryItemProperty;
import hangman.domain.config.CategoryItem;
import hangman.domain.config.ConfigurationItem;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;

import java.util.List;

public class CategoryItemParser implements IConfigurationItemParser {

	private XmlManager categoryXmlManager;

	public CategoryItemParser(String categoryXmlFilePath) {
		this.categoryXmlManager = XmlManager
				.createXmlManager(categoryXmlFilePath);
	}

	@Override
	public CategoryItem getConfigurationItemById(int id) {
		if (this.categoryXmlManager != null) {
			ConfigurationItem configItem = ConfigurationUtility
					.getConfigurationItem(this.categoryXmlManager,
							CategoryItemProperty.ID, String.valueOf(id));
			if (configItem != null && (configItem instanceof CategoryItem)) {
				return ((CategoryItem) configItem);
			}
		}
		return null;
	}

	@Override
	public List<ConfigurationItem> getConfigurationItems() {
		if(this.categoryXmlManager != null) {
			return ConfigurationUtility.getConfigurationItems(
					this.categoryXmlManager, CategoryItemProperty.ID);			
		}
		return null;
	}

}
