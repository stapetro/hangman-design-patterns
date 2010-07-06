package hangman.constants;

public enum GameStateItemProperty {
	
	LANGUAGE_ID("lang_id"), DESCRIPTION("desc"), CONTENT("content");
	
	private String value;

	private GameStateItemProperty(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
