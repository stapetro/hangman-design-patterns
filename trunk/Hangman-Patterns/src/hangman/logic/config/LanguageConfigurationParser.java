package hangman.logic.config;

import hangman.constants.HangmanConstants;
import hangman.constants.LanguageItemProperty;
import hangman.domain.config.LanguageItem;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class LanguageConfigurationParser {

	private ConfigurationParser configParser;
	private XmlManager langXmlManager;

	public LanguageConfigurationParser(ConfigurationParser configParser) {
		this.configParser = configParser;
		String languageXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_LANGUAGE);
		this.langXmlManager = XmlManager.createXmlManager(languageXmlFilePath);
	}

	public List<LanguageItem> getLanguages() {
		if (this.langXmlManager != null) {
			List<Node> languageNodes = this.langXmlManager
					.getNodesByAttribute(LanguageItemProperty.ID.toString());
			if (languageNodes.size() > 0) {
				List<LanguageItem> languageItems = new ArrayList<LanguageItem>();
				for (Node langNode : languageNodes) {
					HashMap<String, String> langProperties = ConfigurationUtility
							.getProperties(langNode);
					languageItems.add(new LanguageItem(langProperties));
				}
				return languageItems;
			}
		}
		return null;
	}

	public LanguageItem getLanguageById(int id) {
		if (this.langXmlManager != null) {
			Node languageNode = this.langXmlManager.getNodeByAttribute(
					LanguageItemProperty.ID.toString(), String.valueOf(id));
			if (languageNode != null) {
				HashMap<String, String> langProperties = ConfigurationUtility
						.getProperties(languageNode);
				return new LanguageItem(langProperties);
			}
		}
		return null;
	}

	// private HashMap<String, String> getLanguageItemProperties(Node langNode)
	// {
	// NamedNodeMap langAttributes = langNode.getAttributes();
	// HashMap<String, String> langProperties = new HashMap<String, String>();
	// Node currentAttr = null;
	// String attrName = null;
	// String attrValue = null;
	// int size = langAttributes.getLength();
	// for (int index = 0; index < size; index++) {
	// currentAttr = langAttributes.item(index);
	// attrName = currentAttr.getNodeName();
	// attrValue = currentAttr.getNodeValue();
	// langProperties.put(attrName, attrValue);
	// }
	// return langProperties;
	// }

}
