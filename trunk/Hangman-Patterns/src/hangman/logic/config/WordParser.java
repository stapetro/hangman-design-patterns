package hangman.logic.config;

import hangman.constants.HangmanConstants;
import hangman.constants.WordItemProperty;
import hangman.domain.WordItem;
import hangman.domain.config.CategoryItem;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.logic.config.item.IConfigurationItemParser;
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
	private IConfigurationItemParser languageConfigParser;
	private IConfigurationItemParser categoryParser;

	public WordParser(ConfigurationParser configParser,
			IConfigurationItemParser langConfigParser,
			IConfigurationItemParser categoryParser) {
		this.configParser = configParser;
		this.languageConfigParser = langConfigParser;
		this.categoryParser = categoryParser;
		String wordXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_WORDS);
		this.wordXmlManager = XmlManager.createXmlManager(wordXmlFilePath);
	}

	// TODO To be improved and separated this method after its full
	// implementation.
	public List<WordItem> getWordsByLanguage(int languageId) {
		if (this.wordXmlManager != null) {		
			String langIdPropertyName = WordItemProperty.LANGUAGE_ID
			.toString();
			List<Node> words = this.wordXmlManager.getNodesByAttribute(
					langIdPropertyName, String
							.valueOf(languageId));
			if (words.size() > 0) {					
				String categoryIdPropertyName = WordItemProperty.CATEGORY_ID
						.toString();
				HashMap<String, String> wordProperties = null;
				List<WordItem> wordItems = new ArrayList<WordItem>();
				for (Node word : words) {					
					wordProperties = ConfigurationUtility.getProperties(word);					
					String langId = wordProperties.get(langIdPropertyName);
					String categoryId = wordProperties
							.get(categoryIdPropertyName);
					CategoryItem categoryItem = null;
					if (categoryId != null) {
						categoryItem = createCategoryItem(categoryId);
						wordProperties.remove(categoryIdPropertyName);
					}
					LanguageItem languageItem = null;
					if (langId != null) {
						languageItem = createLanguageItem(langId);
						wordProperties.remove(langIdPropertyName);
					}
					wordItems.add(new WordItem(wordProperties, languageItem,
							categoryItem));
				}
				return wordItems;
			}
		}
		return null;
	}
	
	private CategoryItem createCategoryItem(String categoryId) {
		try {
			ConfigurationItem configItem = this.categoryParser.getConfigurationItemById(Integer
					.parseInt(categoryId));
			if(configItem != null && configItem instanceof CategoryItem) {
				return (CategoryItem)configItem;
			}
		} catch (NumberFormatException ex) {
			System.out.println("Category with id '" + categoryId
					+ "' cannot be parsed.");							
			ex.printStackTrace();
		}
		return null;
	}
	
	private LanguageItem createLanguageItem(String langId) {
		try {
			ConfigurationItem configItem = this.languageConfigParser
					.getConfigurationItemById(Integer.parseInt(langId));
			if(configItem != null && configItem instanceof LanguageItem) {
				return (LanguageItem)configItem;
			}
		} catch (NumberFormatException ex) {
			System.out.println("Language with id '" + langId
					+ "' cannot be parsed.");
			ex.printStackTrace();
		}		
		return null;
	}

}
