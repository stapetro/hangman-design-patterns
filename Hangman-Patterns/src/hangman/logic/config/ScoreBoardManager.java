package hangman.logic.config;

import java.util.HashMap;

import hangman.constants.HangmanConstants;
import hangman.domain.Player;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ScoreBoardManager {

	private ConfigurationParser configParser;
	private XmlManager scoreBoardXmlManager;

	public ScoreBoardManager(ConfigurationParser configParser) {
		this.configParser = configParser;
		String scoreBoardXmlFilePath = this.configParser
				.getAttributeValue(HangmanConstants.CONFIG_ATTR_NAME_SCORE_BOARD);
		this.scoreBoardXmlManager = XmlManager
				.createXmlManager(scoreBoardXmlFilePath);
	}

	public void getScoreBoardByLevelId(int levelId) {
		if (this.scoreBoardXmlManager != null) {
			Node scoreBoard = this.scoreBoardXmlManager.getNodeByAttribute(
					HangmanConstants.SCORE_BOARD_LEVEL_ID_ATTR, String
							.valueOf(levelId));
			if (scoreBoard.hasChildNodes()) {
				NodeList playerNodes = scoreBoard.getChildNodes();
				HashMap<String, String> playerProperties = null;
				int size = playerNodes.getLength();
				for (int index = 0; index < size; index++) {
					playerProperties = ConfigurationUtility
							.getProperties(playerNodes.item(index));
					new Player(playerProperties);
				}
			}

		}

	}

}
