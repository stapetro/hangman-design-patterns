package hangman.domain.config;

public class LanguageConfigurationItem extends ConfigurationItem {

	private String languageCode;
	private String countryCode;

	public LanguageConfigurationItem() {
		super();
	}

	public LanguageConfigurationItem(int id, String desc, String langCode,
			String countryCode) {
		super(id, desc);
		this.languageCode = langCode;
		this.countryCode = countryCode;
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
}
