package hangman.constants;

public enum WordItemProperty implements IConfigurationItemProperty {

	ID("id"), LANGUAGE_ID("lang_id"), CATEGORY_ID("category_id"), CONTENT("content"), PROVERB(
			"proverb");

	private String value;

	private WordItemProperty(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
