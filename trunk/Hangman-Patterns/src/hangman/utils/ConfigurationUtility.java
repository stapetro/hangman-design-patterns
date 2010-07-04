package hangman.utils;

import java.util.HashMap;

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

}
