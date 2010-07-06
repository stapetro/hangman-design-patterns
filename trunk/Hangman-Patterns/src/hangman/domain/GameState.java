package hangman.domain;

import java.util.HashMap;

import hangman.constants.GameStateItemProperty;
import hangman.logic.WordMask.HangmanMemento;
import hangman.utils.ObjectSerializerUtility;

public class GameState {

	private HangmanMemento gameStateMemento;
	private String description;

	public GameState(HangmanMemento gameStateMemento, String desc) {
		this.gameStateMemento = gameStateMemento;
		this.description = desc;
	}

	public GameState(HashMap<String, String> stateProperties) {
		if (stateProperties.size() > 0) {
			GameStateItemProperty[] gameStatePropNames = GameStateItemProperty
					.values();
			String propValue = null;
			for (GameStateItemProperty propName : gameStatePropNames) {
				propValue = stateProperties.get(propName.toString());
				if (propValue != null) {
					setProperty(propName, propValue);
				}
			}
		}
	}

	public HangmanMemento getGameStateMemento() {
		return gameStateMemento;
	}

	public void setGameStateMemento(HangmanMemento gameStateMemento) {
		this.gameStateMemento = gameStateMemento;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Memento: %s, Desc: %s", this.gameStateMemento,
				this.description);
	}

	private void setProperty(GameStateItemProperty propName, String propValue) {
		switch (propName) {
		case DESCRIPTION:
			this.description = propValue;
			break;
		case CONTENT:
			this.gameStateMemento = (HangmanMemento) ObjectSerializerUtility
					.deserializeObject(propValue);
			break;
		}
	}

}
