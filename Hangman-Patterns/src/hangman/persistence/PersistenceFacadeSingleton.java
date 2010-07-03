package hangman.persistence;

/**
 * Singleton class providing reference to the hangman persistence facade
 */
public class PersistenceFacadeSingleton {
	private static IPersistenceFacade persistanceFacade;

	/**
	 * provide instance of the hangman persistence facade
	 * 
	 * @return IPersistenceFacade instance
	 */
	public static IPersistenceFacade getInstance() {
		if (persistanceFacade == null) {
			persistanceFacade = new PersistenceFacade();
		}

		return persistanceFacade;
	}

}
