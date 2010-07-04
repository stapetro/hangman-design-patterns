package hangman.constants;

public enum CategoryItemProperty implements IConfigurationItemProperty {

	ID("id"), DESCRIPTION("desc"), NAME("name");
	
	private String value;

	private CategoryItemProperty(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
