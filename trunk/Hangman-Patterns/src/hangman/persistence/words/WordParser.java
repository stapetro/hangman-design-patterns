package hangman.persistence.words;

import hangman.constants.HangmanConstants;
import hangman.constants.WordItemProperty;
import hangman.domain.WordItem;
import hangman.domain.config.CategoryItem;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;
import hangman.logic.xml.XmlManager;
import hangman.persistence.config.ConfigurationParser;
import hangman.persistence.config.IConfigurationItemParser;
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

	public WordItem getWord(int wordId) {
		if (this.wordXmlManager != null) {
			Node wordNode = this.wordXmlManager.getNodeByAttribute(
					WordItemProperty.ID.toString(), String.valueOf(wordId));
			if (wordNode != null) {
				return createWordItem(wordNode);
			}
		}
		return null;
	}
	
	public List<WordItem> getWordsByLanguage(int languageId) {
		if (this.wordXmlManager != null) {
			String langIdPropertyName = WordItemProperty.LANGUAGE_ID.toString();
			List<Node> words = this.wordXmlManager.getNodesByAttribute(
					langIdPropertyName, String.valueOf(languageId));
			if (words.size() > 0) {
				List<WordItem> wordItems = new ArrayList<WordItem>();
				for (Node word : words) {
					wordItems.add(createWordItem(word));
				}
				return wordItems;
			}
		}
		return null;
	}

	private WordItem createWordItem(Node wordNode) {
		HashMap<String, String> wordProperties = ConfigurationUtility
				.getProperties(wordNode);

		String langIdPropertyName = WordItemProperty.LANGUAGE_ID.toString();
		String langId = wordProperties.get(langIdPropertyName);
		LanguageItem languageItem = null;
		if (langId != null) {
			languageItem = createLanguageItem(langId);
			wordProperties.remove(langIdPropertyName);
		}

		String categoryIdPropertyName = WordItemProperty.CATEGORY_ID.toString();
		String categoryId = wordProperties.get(categoryIdPropertyName);
		CategoryItem categoryItem = null;
		if (categoryId != null) {
			categoryItem = createCategoryItem(categoryId);
			wordProperties.remove(categoryIdPropertyName);
		}
		return new WordItem(wordProperties, languageItem, categoryItem);
	}

	private CategoryItem createCategoryItem(String categoryId) {
		try {
			ConfigurationItem configItem = this.categoryParser
					.getConfigurationItemById(Integer.parseInt(categoryId));
			if (configItem != null && configItem instanceof CategoryItem) {
				return (CategoryItem) configItem;
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
			if (configItem != null && configItem instanceof LanguageItem) {
				return (LanguageItem) configItem;
			}
		} catch (NumberFormatException ex) {
			System.out.println("Language with id '" + langId
					+ "' cannot be parsed.");
			ex.printStackTrace();
		}
		return null;
	}

}
