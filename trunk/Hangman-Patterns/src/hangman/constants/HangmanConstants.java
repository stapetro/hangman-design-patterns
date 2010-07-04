package hangman.constants;

/**
 * Stores all general and non-context specific constants.
 * 
 * 
 */
public class HangmanConstants implements IConfigurationItemProperty {

	/**
	 * Stores masking symbol which is used in visualizing masked word.
	 */
	public static final char MASK_SYMBOL = '_';

	/**
	 * Stores character buffer size for reading text files.
	 */
	public static final int CHARACTER_BUFFER_SIZE = 2048;

	public static final String ENCODING_UTF_8 = "UTF-8";
	
	public static final String CONFIG_FILE_PATH = "resources/configuration/config.xml";
	public static final String CONFIG_ATTR_NAME = "name";
	public static final String CONFIG_ATTR_VALUE = "value";
	public static final String CONFIG_ATTR_NAME_LANGUAGE = "lang";
	public static final String CONFIG_ATTR_NAME_SETTINGS = "settings";
	public static final String CONFIG_ATTR_NAME_LEVELS = "levels";
	public static final String CONFIG_ATTR_NAME_CATEGORIES = "categories";
	public static final String CONFIG_ATTR_NAME_WORDS = "words";
	public static final String CONFIG_ATTR_NAME_SCORE_BOARD = "scoreboard";
	
	public static final String SETTINGS_LANGUAGE = "lang";
	public static final String SETTINGS_LEVEL = "level";
	public static final String SETTINGS_SCORE_BOARD_SIZE = "scoreboard_size";
	
	public static final String LEVEL_NODE_NAME = "level";
	
	public static final int DEFAULT_SCORE_BOARD_SIZE = 5;
	/**
	 * Used in scoreboard.xml
	 */
	public static final String SCORE_BOARD_LEVEL_ID_ATTR = "level_id";

}
