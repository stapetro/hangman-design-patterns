package hangman.logic.config;

import hangman.constants.HangmanConstants;
import hangman.logic.xml.XmlManager;

import org.w3c.dom.NodeList;

public class SettingsParser {

	private ConfigurationParser configParser;
	private XmlManager settingsXmlManager;

	public SettingsParser(ConfigurationParser configParser) {
		this.configParser = configParser;
		String settingsXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SETTINGS);
		this.settingsXmlManager = XmlManager
				.createXmlManager(settingsXmlFilePath);
	}

	public Integer getCurrentLanguageId() {
		if (this.settingsXmlManager != null) {
			return getSettingsItemNumericValue(HangmanConstants.SETTINGS_LANGUAGE);
		}
		return null;
	}

	public Integer getCurrentLevelId() {
		if (this.settingsXmlManager != null) {
			return getSettingsItemNumericValue(HangmanConstants.SETTINGS_LEVEL);
		}
		return null;
	}

	public Integer getCurrentScoreBoardSize() {
		if (this.settingsXmlManager != null) {
			return getSettingsItemNumericValue(HangmanConstants.SETTINGS_SCORE_BOARD_SIZE);
		}
		return null;
	}

	private Integer getSettingsItemNumericValue(String tagName) {
		NodeList languageNodes = this.settingsXmlManager
				.getNodesByName(tagName);
		if (languageNodes.getLength() > 0) {
			String idTxt = languageNodes.item(0).getTextContent();
			try {
				return Integer.parseInt(idTxt);
			} catch (NumberFormatException ex) {
				System.out.println("Value '" + idTxt + "' cannot be parsed.");
				ex.printStackTrace();
			}
		}
		return null;
	}
}
