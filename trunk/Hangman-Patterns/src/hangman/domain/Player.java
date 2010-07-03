package hangman.domain;

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

	public Player(Player player) {
		this.name = player.getName();
		this.totalMistakes = player.getTotalMistakes();
		this.cheatTheGame = player.isCheatTheGame();
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

	public void incrementTotalMistakes() {
		this.totalMistakes++;
	}

	@Override
	public int compareTo(Player otherPlayer) {		
		return this.totalMistakes.compareTo(otherPlayer.getTotalMistakes());
	}
}
