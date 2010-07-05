package hangman.persistence.scoreboard;

import hangman.constants.HangmanConstants;
import hangman.domain.Player;
import hangman.domain.ScoreBoard;
import hangman.logic.xml.XmlManager;
import hangman.persistence.config.ConfigurationParser;
import hangman.persistence.config.SettingsPersister;
import hangman.utils.ConfigurationUtility;

import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ScoreBoardPersister {

	private ConfigurationParser configParser;
	private XmlManager scoreBoardXmlManager;
	private SettingsPersister settingsParser;

	public ScoreBoardPersister(ConfigurationParser configParser,
			SettingsPersister settingsParser) {
		this.configParser = configParser;
		String scoreBoardXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SCORE_BOARD);
		this.scoreBoardXmlManager = XmlManager
				.createXmlManager(scoreBoardXmlFilePath);
		this.settingsParser = settingsParser;
	}

	public ScoreBoard getScoreBoardByLevelId(int levelId) {
		ScoreBoard scoreBoard = new ScoreBoard();
		if (this.scoreBoardXmlManager != null) {
			Node scoreBoardNode = this.scoreBoardXmlManager.getNodeByAttribute(
					HangmanConstants.SCORE_BOARD_LEVEL_ID_ATTR, String
							.valueOf(levelId));
			if (scoreBoardNode != null && scoreBoardNode.hasChildNodes()) {
				NodeList playerNodes = scoreBoardNode.getChildNodes();
				HashMap<String, String> playerProperties = null;
				Node currentPlayerNode = null;
				int size = playerNodes.getLength();
				for (int index = 0; index < size; index++) {
					currentPlayerNode = playerNodes.item(index);
					if (currentPlayerNode.getNodeType() == Element.ELEMENT_NODE) {
						playerProperties = ConfigurationUtility
								.getProperties(currentPlayerNode);
						scoreBoard.addPlayer(new Player(playerProperties));
					}
				}
			}
		}
		return scoreBoard;
	}

	public ScoreBoard getCurrentScoreBoard() {
		Integer levelId = this.settingsParser.getCurrentLevelId();
		if (levelId == null) {
			levelId = HangmanConstants.DEFAULT_LEVEL_ID;
		}
		return getScoreBoardByLevelId(levelId);
	}
	
	public void addScoreBoard(ScoreBoard scoreBoard, int levelId) {
		if (this.scoreBoardXmlManager != null) {
			Node scoreBoardNode = this.scoreBoardXmlManager.getNodeByAttribute(
					HangmanConstants.SCORE_BOARD_LEVEL_ID_ATTR, String
							.valueOf(levelId));
			if (scoreBoardNode != null) {
				this.scoreBoardXmlManager.removeNode(scoreBoardNode);
			}
			// store attribute name and value
			HashMap<String, String> attributes = new HashMap<String, String>();
			attributes.put(HangmanConstants.SCORE_BOARD_LEVEL_ID_ATTR, String
					.valueOf(levelId));
			scoreBoardNode = this.scoreBoardXmlManager.addNode(
					HangmanConstants.SCORE_BOARD_NODE_NAME, attributes, null);
			// store node name and text content
			HashMap<String, String> childProperties = null;
			List<Player> players = scoreBoard.getTopPlayers();
			for(Player currPlayer : players) {
				childProperties = currPlayer.getProperties();
				this.scoreBoardXmlManager.addNode(
						HangmanConstants.PLAYER_NODE_NAME, null, childProperties,
						scoreBoardNode);
			}								
			this.scoreBoardXmlManager.writeDocumentToXmlFile();
		}		
	}
}
