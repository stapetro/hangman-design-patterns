package hangman.utils;

import hangman.constants.IConfigurationItemProperty;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.ConfigurationItemFactory;
import hangman.logic.xml.XmlManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationUtility {

	public static HashMap<String, String> getProperties(Node xmlNode) {
		HashMap<String, String> levelProperties = new HashMap<String, String>();
		if (xmlNode.hasAttributes() == true) {
			NamedNodeMap levelAttributes = xmlNode.getAttributes();
			Node currentAttr = null;
			int levelAttrSize = levelAttributes.getLength();
			for (int index = 0; index < levelAttrSize; index++) {
				currentAttr = levelAttributes.item(index);
				levelProperties.put(currentAttr.getNodeName(), currentAttr
						.getNodeValue());
			}
		}
		if (xmlNode.hasChildNodes() == true) {
			NodeList levelChildren = xmlNode.getChildNodes();
			Node currentChild = null;
			int levelsSize = levelChildren.getLength();
			for (int index = 0; index < levelsSize; index++) {
				currentChild = levelChildren.item(index);
				if (currentChild.getNodeType() == Element.ELEMENT_NODE) {
					levelProperties.put(currentChild.getNodeName(),
							currentChild.getTextContent());
				}
			}
		}
		return levelProperties;
	}

	public static ConfigurationItem getConfigurationItem(XmlManager xmlManager,
			IConfigurationItemProperty attributeName, String attributeValue) {
		Node configurationNode = xmlManager.getNodeByAttribute(attributeName
				.toString(), attributeValue);
		if (configurationNode != null) {
			HashMap<String, String> configurationItemProperties = ConfigurationUtility
					.getProperties(configurationNode);
			return ConfigurationItemFactory.createConfigurationItem(attributeName,
					configurationItemProperties);
		}
		return null;
	}

	public static List<ConfigurationItem> getConfigurationItems(
			XmlManager xmlManager, IConfigurationItemProperty attributeName) {
		List<Node> languageNodes = xmlManager.getNodesByAttribute(attributeName
				.toString());		
		if (languageNodes.size() > 0) {
			ConfigurationItem currentConfigItem = null;
			List<ConfigurationItem> configItems = new ArrayList<ConfigurationItem>();
			for (Node langNode : languageNodes) {
				HashMap<String, String> configItemProperties = ConfigurationUtility
				.getProperties(langNode);
				currentConfigItem = ConfigurationItemFactory.createConfigurationItem(attributeName, configItemProperties);
				configItems.add(currentConfigItem);
			}					
			return configItems;
		}
		return null;
	}

}
