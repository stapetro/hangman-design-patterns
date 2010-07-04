package hangman.logic.config;

import hangman.constants.HangmanConstants;
import hangman.domain.config.LevelItem;
import hangman.logic.xml.XmlManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;
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
					levelProperties = getLevelsProperties(levels.item(index));
					levelItems.add(new LevelItem(levelProperties));
				}
				return levelItems;
			}
		}
		return null;
	}

	private HashMap<String, String> getLevelsProperties(Node levelNode) {
		NodeList levelChildren = levelNode.getChildNodes();
		Node currentChild = null;
		int size = levelChildren.getLength();
		HashMap<String, String> levelProperties = new HashMap<String, String>();
		for (int index = 0; index < size; index++) {
			currentChild = levelChildren.item(index);
			if (currentChild.getNodeType() == Element.ELEMENT_NODE) {
				levelProperties.put(currentChild.getNodeName(), currentChild
						.getTextContent());
			}
		}
		return levelProperties;
	}

}
