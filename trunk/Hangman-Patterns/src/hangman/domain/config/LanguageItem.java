package hangman.domain.config;

import hangman.constants.LanguageItemProperty;

import java.util.HashMap;

public class LanguageItem extends ConfigurationItem {

	private String languageCode;
	private String countryCode;

	public LanguageItem() {
		super();
	}

	public LanguageItem(int id, String desc, String langCode, String countryCode) {
		super(id, desc);
		this.languageCode = langCode;
		this.countryCode = countryCode;
	}

	public LanguageItem(HashMap<String, String> langProps) {
		if (langProps.size() > 0) {
			LanguageItemProperty[] langPropertyNames = LanguageItemProperty
					.values();
			String propValue = null;
			for (LanguageItemProperty langProp : langPropertyNames) {
				propValue = langProps.get(langProp.toString());
				if (propValue != null) {
					setProperty(langProp, propValue);
				}
			}
		}
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return String.format(
				"Id: %d, Lang code: %s, Country code: %s, Desc: %s", getId(),
				this.languageCode, this.countryCode, getDescription());
	}

	private void setProperty(LanguageItemProperty langProp, String propValue) {
		switch (langProp) {
		case ID:
			try {
				setId(Integer.parseInt(propValue));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			break;
		case DESCRIPTION:
			setDescription(propValue);
			break;
		case CODE:
			setLanguageCode(propValue);
			break;
		case COUNTRY_CODE:
			setCountryCode(propValue);
			break;
		}
	}

}
