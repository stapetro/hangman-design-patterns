package hangman.domain;

import hangman.constants.PlayerProperty;

import java.util.HashMap;

/**
 * Represents player of hangman game.
 * 
 * @author Stanislav Petrov
 * 
 */
public class Player implements Comparable<Player> {

	private String name;
	private Integer totalMistakes;
	private boolean cheatTheGame;

	/**
	 * Initializes player's information to default values.
	 */
	public Player() {
		this.name = "";
		this.totalMistakes = 0;
		this.cheatTheGame = false;
	}

	public Player(String name, int totalMistakes) {
		this();
		this.name = name;
		this.totalMistakes = totalMistakes;
	}

	public Player(Player player) {
		this(player.getName(), player.getTotalMistakes());
		this.cheatTheGame = player.isCheatTheGame();
	}

	public Player(HashMap<String, String> playerProperties) {
		if (playerProperties.size() > 0) {
			PlayerProperty[] playerPropertyNames = PlayerProperty.values();
			String propValue = null;
			for (PlayerProperty playerPropName : playerPropertyNames) {
				propValue = playerProperties.get(playerPropName.toString());
				if (propValue != null) {
					setProperty(playerPropName, propValue);
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

	public void setCheatTheGame(boolean cheatTheGame) {
		this.cheatTheGame = cheatTheGame;
	}

	public boolean isCheatTheGame() {
		return this.cheatTheGame;
	}

	public Integer getTotalMistakes() {
		return this.totalMistakes;
	}

	public HashMap<String, String> getProperties() {
		HashMap<String, String> playerProperties = new HashMap<String, String>();
		if ((this.name != null && this.name.length() > 0)
				&& this.totalMistakes != null) {
			playerProperties.put(PlayerProperty.NAME.toString(), this.name);
			playerProperties.put(PlayerProperty.TOTAL_MISTAKES.toString(),
					this.totalMistakes.toString());
		}
		return playerProperties;
	}

	public void incrementTotalMistakes() {
		this.totalMistakes++;
	}

	@Override
	public int compareTo(Player otherPlayer) {
		Integer otherPlayerTotalMistakes = otherPlayer.getTotalMistakes();
		if (this.totalMistakes == null && otherPlayerTotalMistakes != null) {
			return -1;
		} else if (this.totalMistakes != null
				&& otherPlayerTotalMistakes == null) {
			return 1;
		} else if (this.totalMistakes == null
				&& otherPlayerTotalMistakes == null) {
			return 0;
		}
		return this.totalMistakes.compareTo(otherPlayer.getTotalMistakes());
	}

	public String toString() {
		return String.format(
				"Player name: %s, Total mistakes: %d, Cheated: %b", this.name,
				this.totalMistakes, this.cheatTheGame);
	}

	private void setProperty(PlayerProperty playerPropName, String propValue) {
		switch (playerPropName) {
		case NAME:
			this.name = propValue;
			break;
		case TOTAL_MISTAKES:
			try {
				this.totalMistakes = Integer.valueOf(propValue);
			} catch (NumberFormatException ex) {
				System.out.println("Total mistakes property value '"
						+ propValue + "' cannot be parsed.");
				ex.printStackTrace();
			}
			break;
		}
	}
}
