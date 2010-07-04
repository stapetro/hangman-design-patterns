package hangman.domain;

import java.util.HashMap;

import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;

public class WordItem {

	private LanguageItem language;
	private ConfigurationItem category;
	private String content;
	private String proverb;

	public WordItem(String content, String proverb, LanguageItem lang,
			ConfigurationItem category) {
		this.content = content;
		this.proverb = proverb;
		this.language = lang;
		this.category = category;
	}

	public WordItem(HashMap<String, String> wordProperties,
			LanguageItem languageItem, ConfigurationItem categoryItem) {
		if (wordProperties.size() > 0) {
			this.language = languageItem;
			this.category = categoryItem;
		}
	}

	public WordItem(HashMap<String, String> wordProperties,
			LanguageItem languageItem) {
		this(wordProperties, languageItem, null);
	}

	public LanguageItem getLanguage() {
		return language;
	}

	public void setLanguage(LanguageItem language) {
		this.language = language;
	}

	public ConfigurationItem getCategory() {
		return category;
	}

	public void setCategory(ConfigurationItem category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProverb() {
		return proverb;
	}

	public void setProverb(String proverb) {
		this.proverb = proverb;
	}	

}
