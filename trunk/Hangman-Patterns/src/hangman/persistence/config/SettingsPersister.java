package hangman.persistence.config;

import hangman.constants.HangmanConstants;
import hangman.logic.xml.XmlManager;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SettingsPersister {

	private ConfigurationParser configParser;
	private XmlManager settingsXmlManager;

	public SettingsPersister(ConfigurationParser configParser) {
		this.configParser = configParser;
		String settingsXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SETTINGS);
		this.settingsXmlManager = XmlManager
				.createXmlManager(settingsXmlFilePath);
	}

	public Integer getCurrentLanguageId() {
		return getSettingsItemNumericValue(HangmanConstants.SETTINGS_LANGUAGE);
	}

	public void setCurrentLanguageId(int languageId) {
		setSettingsItemValue(HangmanConstants.SETTINGS_LANGUAGE, String
				.valueOf(languageId));
	}

	public Integer getCurrentLevelId() {
		return getSettingsItemNumericValue(HangmanConstants.SETTINGS_LEVEL);
	}

	public void setCurrentLevelId(int levelId) {
		setSettingsItemValue(HangmanConstants.SETTINGS_LEVEL, String
				.valueOf(levelId));
	}

	public Integer getCurrentScoreBoardSize() {
		return getSettingsItemNumericValue(HangmanConstants.SETTINGS_SCORE_BOARD_SIZE);
	}

	public void setCurrentScoreBoardSize(int scoreBoardSize) {
		setSettingsItemValue(HangmanConstants.SETTINGS_SCORE_BOARD_SIZE, String
				.valueOf(scoreBoardSize));
	}

	private Integer getSettingsItemNumericValue(String tagName) {
		if (this.settingsXmlManager != null) {
			NodeList languageNodes = this.settingsXmlManager
					.getNodesByName(tagName);
			if (languageNodes.getLength() > 0) {
				String idTxt = languageNodes.item(0).getTextContent();
				try {
					return Integer.parseInt(idTxt);
				} catch (NumberFormatException ex) {
					System.out.println("Value '" + idTxt
							+ "' cannot be parsed.");
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	private void setSettingsItemValue(String tagName, String value) {
		if (this.settingsXmlManager != null) {
			Node languageNode = this.settingsXmlManager.getNodeByName(tagName);
			System.out.println("save...");
			if (languageNode != null) {
				languageNode.setTextContent(value);
				this.settingsXmlManager.writeDocumentToXmlFile();
			}
		}
	}
}
