package hangman.constants;

public enum ConfigurationItemProperty {
	
	ID("id"), DESCRIPTION("desc");
	
	private String value;

	private ConfigurationItemProperty(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
