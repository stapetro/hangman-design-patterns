package hangman.domain;

import java.util.HashMap;

import hangman.constants.WordItemProperty;
import hangman.domain.config.ConfigurationItem;
import hangman.domain.config.LanguageItem;

public class WordItem {

	private int id;
	private LanguageItem language;
	private ConfigurationItem category;
	private String content;
	private String proverb;

	public WordItem() {
		this("test", "testvane mu e maikata", null, null);
	}

	public WordItem(String content, String proverb, LanguageItem lang,
			ConfigurationItem category) {
		this.id = -1;
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
			WordItemProperty[] wordPropNames = WordItemProperty.values();
			String propValue = null;
			for (WordItemProperty wordPropName : wordPropNames) {
				propValue = wordProperties.get(wordPropName.toString());
				if (propValue != null) {
					setProperty(wordPropName, propValue);
				}
			}
		}
	}

	public WordItem(HashMap<String, String> wordProperties,
			LanguageItem languageItem) {
		this(wordProperties, languageItem, null);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return String
				.format(
						"Word id: %d, Content: %s, Proverb: %s%nLanguage: %s%nCategory: %s%n",
						this.id, this.content, this.proverb,
						((this.language != null) ? this.language : ""),
						((this.category != null) ? this.category : ""));
	}

	private void setProperty(WordItemProperty wordPropertyName, String propValue) {
		switch (wordPropertyName) {
		case ID:
			try {
				this.id = Integer.parseInt(propValue);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			break;
		case CONTENT:
			this.content = propValue;
			break;
		case PROVERB:
			this.proverb = propValue;
			break;
		}
	}

}
