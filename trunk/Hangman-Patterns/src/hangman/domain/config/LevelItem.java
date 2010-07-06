package hangman.domain.config;

import hangman.constants.LevelItemProperty;

import java.util.HashMap;

public class LevelItem extends ConfigurationItem {

	private String name;
	private int minWordLength;
	private int maxWordLength;
	private int totalMistakes;

	public LevelItem() {
		super();
	}

	public LevelItem(int id, String desc, String name, int minWordLength,
			int maxWordLength, int totalMistakes) {
		super(id, desc);
		this.name = name;
		this.minWordLength = minWordLength;
		this.maxWordLength = maxWordLength;
		this.totalMistakes = totalMistakes;
	}

	public LevelItem(int id, String name, int wordLength, int maxWordLength,
			int totalMistakes) {
		this(id, "", name, wordLength, maxWordLength, totalMistakes);
	}

	public LevelItem(HashMap<String, String> levelProperties) {
		if (levelProperties.size() > 0) {
			LevelItemProperty[] levelItemProps = LevelItemProperty.values();
			String levelPropValue = null;
			for (LevelItemProperty levelProp : levelItemProps) {
				levelPropValue = levelProperties.get(levelProp.toString());
				if (levelPropValue != null) {
					setProperty(levelProp, levelPropValue);
				}
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinWordLength() {
		return minWordLength;
	}

	public void setMinWordLength(int wordLength) {
		this.minWordLength = wordLength;
	}

	public int getMaxWordLength() {
		return maxWordLength;
	}

	public void setMaxWordLength(int maxWordLength) {
		this.maxWordLength = maxWordLength;
	}

	public int getTotalMistakes() {
		return totalMistakes;
	}

	public void setTotalMistakes(int totalMistakes) {
		this.totalMistakes = totalMistakes;
	}

	@Override
	public String toString() {
		return String
				.format(
						"Id: %d, Desc: %s, Name: %s, Word length: [%d, %d], Total mistakes: %s%n",
						getId(), getDescription(), this.name,
						this.minWordLength, this.maxWordLength,
						this.totalMistakes);
	}

	private void setProperty(LevelItemProperty levelProp, String levelPropValue) {
		String errorMsg = "Value '" + levelPropValue + "' cannot be parsed.";
		switch (levelProp) {
		case ID:
			try {
				setId(Integer.parseInt(levelPropValue));
			} catch (NumberFormatException ex) {
				System.out.println(errorMsg);
				ex.printStackTrace();
			}
			break;
		case DESCRIPTION:
			setDescription(levelPropValue);
			break;
		case NAME:
			this.name = levelPropValue;
			break;
		case WORD_LENGTH:
			String[] wordSizes = levelPropValue.split(",");
			if (wordSizes.length > 0) {
				try {
					this.minWordLength = Integer.parseInt(wordSizes[0]);
					this.maxWordLength = Integer.parseInt(wordSizes[1]);
				} catch (NumberFormatException ex) {
					System.out.println(errorMsg);
					ex.printStackTrace();
				}
			}
			break;
		case TOTAL_MISTAKES:
			try {
				this.totalMistakes = Integer.parseInt(levelPropValue);
			} catch (NumberFormatException ex) {
				System.out.println(errorMsg);
				ex.printStackTrace();
			}
			break;
		}
	}
}
