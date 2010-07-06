package hangman.domain;

import java.util.HashMap;

import hangman.constants.GameStateItemProperty;
import hangman.logic.WordMask.Memento;
import hangman.utils.ObjectSerializerUtility;

public class GameState {

	private Memento gameStateMemento;
	private String description;

	public GameState(Memento gameStateMemento, String desc) {
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

	public Memento getGameStateMemento() {
		return gameStateMemento;
	}

	public void setGameStateMemento(Memento gameStateMemento) {
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
			this.gameStateMemento = (Memento) ObjectSerializerUtility
					.deserializeObject(propValue);
			break;
		}
	}

}
