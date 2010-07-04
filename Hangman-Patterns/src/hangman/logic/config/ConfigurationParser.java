package hangman.logic.config;

import java.util.List;

import hangman.constants.HangmanConstants;
import hangman.domain.config.ConfigurationItem;
import hangman.logic.xml.XmlManager;

import org.w3c.dom.Node;

/**
 * Represents hangman configuration parser which gets necessary information from
 * config.xml
 * 
 * @author Stanislav Petrov
 * 
 */
public class ConfigurationParser {

	private XmlManager xmlManager;

	public ConfigurationParser() {
		this.xmlManager = XmlManager
				.createXmlManager(HangmanConstants.CONFIG_FILE_PATH);
	}

	/**
	 * Gets value of configuration value attribute by specifying value of
	 * configuration name attribute
	 * 
	 * @param nameAttribute
	 *            Value of configuration name attribute to be specified.
	 * @return Null - if configuration attribute is not present.
	 */
	public String getAttributeValue(String nameAttribute) {
		if (this.xmlManager != null) {
			Node xmlNode = this.xmlManager.getNodeByAttribute(
					HangmanConstants.CONFIG_ATTR_NAME, nameAttribute);
			if (xmlNode != null) {
				Node valueAttr = this.xmlManager.getAttributeByNode(xmlNode,
						HangmanConstants.CONFIG_ATTR_VALUE);
				return valueAttr.getNodeValue();
			}
		}
		return "";
	}
}
