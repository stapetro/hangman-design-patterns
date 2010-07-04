package hangman.constants;

public enum LevelItemProperty implements IConfigurationItemProperty {

	ID("id"), DESCRIPTION("desc"), NAME("name"), WORD_LENGTH("word_length"), TOTAL_MISTAKES(
			"total_mistakes");

	private String value;

	private LevelItemProperty(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
