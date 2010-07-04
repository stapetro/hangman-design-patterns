package hangman.persistence.config;

import hangman.constants.HangmanConstants;
import hangman.constants.LevelItemProperty;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.ConfigurationItemFactory;
import hangman.domain.config.LevelItem;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.NodeList;

public class LevelItemParser implements IConfigurationItemParser {

	private ConfigurationParser configParser;
	private XmlManager levelXmlManager;

	public LevelItemParser(ConfigurationParser configParser) {
		this.configParser = configParser;
		String levelsXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_LEVELS);
		this.levelXmlManager = XmlManager.createXmlManager(levelsXmlFilePath);
	}

//	public List<LevelItem> getLevels() {
//		if (this.levelXmlManager != null) {
//			NodeList levels = this.levelXmlManager.getNodesByName("level");
//			int size = levels.getLength();
//			if (size > 0) {
//				List<LevelItem> levelItems = new ArrayList<LevelItem>();
//				HashMap<String, String> levelProperties = null;
//				for (int index = 0; index < size; index++) {
//					levelProperties = ConfigurationUtility.getProperties(levels
//							.item(index));
//					levelItems.add(new LevelItem(levelProperties));
//				}
//				return levelItems;
//			}
//		}
//		return null;
//	}
//
//	public LevelItem getLevelById(int levelId) {
//		if (this.levelXmlManager != null) {
//			ConfigurationItem configItem = ConfigurationUtility
//					.getConfigurationItem(this.levelXmlManager,
//							LevelItemProperty.ID, String.valueOf(levelId));
//			if (configItem != null && (configItem instanceof LevelItem)) {
//				return ((LevelItem) configItem);
//			}
//		}
//		return null;
//	}

	@Override
	public LevelItem getConfigurationItemById(int levelId) {
		if (this.levelXmlManager != null) {
			ConfigurationItem configItem = ConfigurationUtility
					.getConfigurationItem(this.levelXmlManager,
							LevelItemProperty.ID, String.valueOf(levelId));
			if (configItem != null && (configItem instanceof LevelItem)) {
				return ((LevelItem) configItem);
			}
		}
		return null;
	}

	@Override
	public List<ConfigurationItem> getConfigurationItems() {
		if (this.levelXmlManager != null) {
			NodeList levels = this.levelXmlManager
					.getNodesByName(HangmanConstants.LEVEL_NODE_NAME);
			int size = levels.getLength();
			if (size > 0) {
				List<ConfigurationItem> levelItems = new ArrayList<ConfigurationItem>();
				HashMap<String, String> levelProperties = null;
				ConfigurationItem configItem = null;
				for (int index = 0; index < size; index++) {
					levelProperties = ConfigurationUtility.getProperties(levels
							.item(index));
					configItem = ConfigurationItemFactory
							.createConfigurationItem(LevelItemProperty.ID,
									levelProperties);
					levelItems.add(configItem);
				}
				return levelItems;
			}
		}
		return null;
	}

}
