package hangman.domain;

import hangman.persistence.IPersistenceFacade;
import hangman.persistence.PersistenceFacadeSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents score board for hangman game. Stores sorted players in ascending
 * order by their number of mistakes.
 * 
 * @author Stanislav Petrov
 * 
 */
public class ScoreBoard {

	private int size;
	private List<Player> topPlayers;
	private IPersistenceFacade persistenceFacade;

	/**
	 * Gets size from persistence facade.
	 */
	public ScoreBoard() {
		this.persistenceFacade = PersistenceFacadeSingleton.getInstance();
		this.size = this.persistenceFacade.getCurrentScoreBoardSize();
		this.topPlayers = new ArrayList<Player>(this.size + 1);
	}
	
	public List<Player> getTopPlayers() {
		return new ArrayList<Player>(this.topPlayers);
	}

	/**
	 * Adds player to score board in a sorted way. If new playe is added, it is
	 * also persisted.
	 * 
	 * @param player
	 */
	public void addPlayer(Player player) {
		int numberOfPlayers = this.topPlayers.size();
		boolean canAddPlayer = false;
		if (numberOfPlayers >= this.size) {
			int lastIndex = numberOfPlayers - 1;
			Player lastPlayer = this.topPlayers.get(lastIndex);
			if (lastPlayer.compareTo(player) > 0) {
				this.topPlayers.remove(lastIndex);
				canAddPlayer = true;
			}
		} else {
			canAddPlayer = true;
		}
		if (canAddPlayer == true) {
			Player newPlayer = new Player(player);
			this.topPlayers.add(newPlayer);
			sortPlayers();			
		}
	}
	
	/**
	 * Save score board to persistence
	 */
	public void save() {
		this.persistenceFacade.addScoreBoard(this);
	}

	@Override
	public String toString() {
		String output = "Scoreboard:\n";
		Player currentPlayer = null;
		int size = topPlayers.size();
		Integer currentNumberOfMistakes = 0;
		for (int index = 0; index < size; index++) {
			currentPlayer = topPlayers.get(index);
			currentNumberOfMistakes = currentPlayer.getTotalMistakes();
			output += (currentPlayer.getName() + " ---> "
					+ currentNumberOfMistakes + " mistake");
			if (currentNumberOfMistakes != null) {
				output += ((currentNumberOfMistakes == 1) ? "" : "s");
			}
			output += "\n";
		}
		return output;
	}

	private void sortPlayers() {
		Collections.sort(topPlayers);
	}	

}
