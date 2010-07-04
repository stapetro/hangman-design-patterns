package hangman.logic.config;

import hangman.constants.HangmanConstants;
import hangman.constants.LanguageItemProperty;
import hangman.constants.LevelItemProperty;
import hangman.domain.config.LanguageItem;
import hangman.domain.config.LevelItem;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LevelConfigurationParser {

	private ConfigurationParser configParser;
	private XmlManager levelXmlManager;

	public LevelConfigurationParser(ConfigurationParser configParser) {
		this.configParser = configParser;
		String levelsXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_LEVELS);
		this.levelXmlManager = XmlManager.createXmlManager(levelsXmlFilePath);
	}

	public List<LevelItem> getLevels() {
		if (this.levelXmlManager != null) {
			NodeList levels = this.levelXmlManager.getNodesByName("level");
			int size = levels.getLength();
			if (size > 0) {
				List<LevelItem> levelItems = new ArrayList<LevelItem>();
				HashMap<String, String> levelProperties = null;
				for (int index = 0; index < size; index++) {
					levelProperties = ConfigurationUtility.getProperties(levels
							.item(index));
					levelItems.add(new LevelItem(levelProperties));
				}
				return levelItems;
			}
		}
		return null;
	}

	public LevelItem getLevelById(int levelId) {
		if (this.levelXmlManager != null) {
			Node levelNode = this.levelXmlManager.getNodeByAttribute(
					LevelItemProperty.ID.toString(), String.valueOf(levelId));
			if (levelNode != null) {
				HashMap<String, String> langProperties = ConfigurationUtility
						.getProperties(levelNode);
				return new LevelItem(langProperties);
			}
		}
		return null;
	}

}
