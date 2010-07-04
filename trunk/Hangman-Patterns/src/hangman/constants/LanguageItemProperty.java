package hangman.constants;

public enum LanguageItemProperty implements IConfigurationItemProperty {

	ID("id"), DESCRIPTION("desc"), CODE("code"), COUNTRY_CODE("country_code");

	private String value;

	private LanguageItemProperty(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
