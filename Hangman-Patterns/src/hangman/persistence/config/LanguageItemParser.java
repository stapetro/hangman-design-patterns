package hangman.persistence.config;

import hangman.constants.LanguageItemProperty;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;

import java.util.List;

public class LanguageItemParser implements IConfigurationItemParser {

	private XmlManager langXmlManager;

	public LanguageItemParser(String languageXmlFilePath) {
		this.langXmlManager = XmlManager.createXmlManager(languageXmlFilePath);
	}

	@Override
	public LanguageItem getConfigurationItemById(int id) {
		if (this.langXmlManager != null) {
			ConfigurationItem configItem = ConfigurationUtility
					.getConfigurationItem(this.langXmlManager,
							LanguageItemProperty.ID, String.valueOf(id));
			if (configItem != null && (configItem instanceof LanguageItem)) {
				return ((LanguageItem) configItem);
			}
		}
		return null;
	}

	@Override
	public List<ConfigurationItem> getConfigurationItems() {
		if (this.langXmlManager != null) {
			return ConfigurationUtility.getConfigurationItems(
					this.langXmlManager, LanguageItemProperty.ID);
		}
		return null;
	}
}
