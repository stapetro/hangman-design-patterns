package hangman.languages;

import hangman.domain.config.LanguageItem;
import hangman.persistence.IPersistenceFacade;
import hangman.persistence.PersistenceFacadeSingleton;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This factory class is responsible for providing resources for
 * internationalization based on the current language configurations
 */
public class LanguageResourcesFactory {

	/**
	 * Resource bundle base name
	 */
	private static final String RESOURCE_BUNDLE_FILE = "Hangman";

	/**
	 * Provide a resource bundle object for the specified language settings in
	 * the configuration files
	 * 
	 * @return ResourceBundle instance for internationalization
	 */
	public ResourceBundle getLanguageResource() {
		Locale locale = getLocale();
		ResourceBundle languageResourceBundle = ResourceBundle.getBundle(
				RESOURCE_BUNDLE_FILE, locale);

		return languageResourceBundle;
	}

	/**
	 * Get the currently set language locale
	 * 
	 * @return the Locale set in configuration files
	 */
	private Locale getLocale() {
		IPersistenceFacade persistenceFacade = PersistenceFacadeSingleton
				.getInstance();
		LanguageItem languageConfiguration = persistenceFacade
				.getCurrentLanguage();

		String languageCode = languageConfiguration.getLanguageCode();
		String countryCode = languageConfiguration.getCountryCode();
		Locale locale = new Locale(languageCode, countryCode);

		return locale;
	}
}
