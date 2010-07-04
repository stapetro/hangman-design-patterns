package hangman.constants;

public enum PlayerProperty {
	NAME("name"), TOTAL_MISTAKES("total_mistakes");
	
	private String value;

	private PlayerProperty(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
