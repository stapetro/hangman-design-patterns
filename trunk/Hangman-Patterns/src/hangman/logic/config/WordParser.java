package hangman.logic.config;

import hangman.constants.HangmanConstants;
import hangman.constants.WordItemProperty;
import hangman.domain.WordItem;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Node;

/**
 * Parses wrods.xml
 * 
 */
public class WordParser {

	private ConfigurationParser configParser;
	private XmlManager wordXmlManager;
	private LanguageConfigurationParser languageConfigParser;

	public WordParser(ConfigurationParser configParser,
			LanguageConfigurationParser langConfigParser,
			XmlManager wordXmlManager) {
		this.configParser = configParser;
		this.languageConfigParser = langConfigParser;
		String wordXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_WORDS);
		this.wordXmlManager = XmlManager.createXmlManager(wordXmlFilePath);
	}

	// TODO To be improved and separated this method after its full
	// implementation.
	public List<WordItem> getWordsByLanguage(int languageId) {
		if (this.wordXmlManager != null) {
			List<Node> words = this.wordXmlManager.getNodesByAttribute(
					HangmanConstants.SETTINGS_LANGUAGE, String
							.valueOf(languageId));
			if (words.size() > 0) {
				String langIdPropertyName = WordItemProperty.LANGUAGE_ID
						.toString();
				String categoryIdPropertyName = WordItemProperty.CATEGORY_ID
						.toString();
				HashMap<String, String> wordProperties = null;
				List<WordItem> wordItems = new ArrayList<WordItem>();
				for (Node word : words) {
					wordProperties = ConfigurationUtility.getProperties(word);
					String langId = wordProperties.get(langIdPropertyName);
					String categoryId = wordProperties
							.get(categoryIdPropertyName);
					ConfigurationItem categoryItem = null;
					if (categoryId != null) {
						try {
							Integer.parseInt(categoryId);
							// TODO get category by ID.
							wordProperties.remove(categoryIdPropertyName);
						} catch (NumberFormatException ex) {
							ex.printStackTrace();
						}
					}
					LanguageItem languageItem = null;
					if (langId != null) {
						try {
							languageItem = this.languageConfigParser
									.getLanguageById(Integer.parseInt(langId));
							wordProperties.remove(langIdPropertyName);
						} catch (NumberFormatException ex) {
							System.out.println("Value '" + langId
									+ "' cannot be parsed.");
							ex.printStackTrace();
						}
					}
					wordItems.add(new WordItem(wordProperties, languageItem,
							categoryItem));
				}
				return wordItems;
			}
		}
		return null;
	}

}
