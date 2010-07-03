package hangman.constants;

/**
 * Stores all general and non-context specific constants.
 * 
 * @author Stanislav Petrov
 * 
 */
public class HangmanConstants {

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

}
