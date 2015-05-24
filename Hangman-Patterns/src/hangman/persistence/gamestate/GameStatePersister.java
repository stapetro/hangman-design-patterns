package hangman.persistence.gamestate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Node;

import hangman.constants.GameStateItemProperty;
import hangman.constants.HangmanConstants;
import hangman.domain.GameState;
import hangman.logic.WordMask.HangmanMemento;
import hangman.logic.xml.XmlManager;
import hangman.utils.ConfigurationUtility;
import hangman.utils.ObjectSerializerUtility;
import hangman.utils.base64Coder.Base64Coder;

public class GameStatePersister {

	private XmlManager gameStateXmlManager;

	public GameStatePersister(String gameStateXmlFilePath) {
		this.gameStateXmlManager = XmlManager
				.createXmlManager(gameStateXmlFilePath);
	}

	public void saveGameState(GameState gameState, int languageId) {
		if (this.gameStateXmlManager != null) {
			HashMap<String, String> attributes = new HashMap<String, String>();
			attributes.put(GameStateItemProperty.LANGUAGE_ID.toString(), String
					.valueOf(languageId));
			HashMap<String, String> properties = new HashMap<String, String>();
			HangmanMemento gameStateMemento = gameState.getGameStateMemento();
			String serializedContent = ObjectSerializerUtility
					.serializeOject(gameStateMemento);
			String encodedSerializedContent = Base64Coder.encodeString(serializedContent);
			properties.put(GameStateItemProperty.CONTENT.toString(),
					encodedSerializedContent);
			String description = gameState.getDescription();
			properties.put(GameStateItemProperty.DESCRIPTION.toString(),
					description);
			this.gameStateXmlManager.addNode(
					HangmanConstants.GAME_STATE_TAG_NAME, attributes,
					properties);
			this.gameStateXmlManager.writeDocumentToXmlFile();
		}
	}

	public List<GameState> getSavedGameStates(int languageId) {
		if (this.gameStateXmlManager != null) {
			List<Node> stateNodes = this.gameStateXmlManager
					.getNodesByAttribute(GameStateItemProperty.LANGUAGE_ID
							.toString(), String.valueOf(languageId));
			if (stateNodes != null && stateNodes.size() > 0) {
				List<GameState> gameStates = new ArrayList<GameState>();
				HashMap<String, String> stateProperties = null;
				for (Node stateNode : stateNodes) {
					stateProperties = ConfigurationUtility
							.getProperties(stateNode);		
					decodeGameStateContent(stateProperties);
					gameStates.add(new GameState(stateProperties));
				}
				return gameStates;
			}
		}
		return null;
	}
	
	private void decodeGameStateContent(HashMap<String, String> stateProperties) {
		String encodedContent = stateProperties.get(GameStateItemProperty.CONTENT.toString());
		if(encodedContent != null) {
			String decodedContent = Base64Coder.decodeString(encodedContent);
			stateProperties.put(GameStateItemProperty.CONTENT.toString(), decodedContent);
		}
		
	}

}
