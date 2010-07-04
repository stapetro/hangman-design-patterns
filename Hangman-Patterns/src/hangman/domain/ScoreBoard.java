package hangman.domain;

import hangman.constants.HangmanConstants;

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

	public ScoreBoard() {
		this(HangmanConstants.DEFAULT_SCORE_BOARD_SIZE);
	}

	public ScoreBoard(int scoreBoardSize) {
		this.size = scoreBoardSize;
		topPlayers = new ArrayList<Player>(this.size + 1);
	}

	/**
	 * Adds player to score board in a sorted way.
	 * 
	 * @param player
	 */
	public void addPlayer(Player player) {
		int numberOfPlayers = this.topPlayers.size();
		if (numberOfPlayers >= this.size) {
			int lastIndex = numberOfPlayers - 1;
			Player lastPlayer = this.topPlayers.get(lastIndex);
			if (lastPlayer.compareTo(player) > 0) {
				this.topPlayers.remove(lastIndex);
			}
		}
		this.topPlayers.add(new Player(player));
		sortPlayers();
	}

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
			if(currentNumberOfMistakes != null) {
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
