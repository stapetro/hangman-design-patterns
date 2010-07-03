package hangman.logic.xml;

import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Stores all validation errors while parsing XML data.
 * 
 * @author Stanislav Petrov
 * @date 20091126
 * 
 */
public class ValidationHandler extends DefaultHandler {

	private java.util.List<SAXParseException> errors;
	private boolean validationFailed = false;

	public ValidationHandler() {
		errors = new ArrayList<SAXParseException>();
	}

	/**
	 * Checks if validation failed.
	 * 
	 * @return True - validation failed, false - otherwise.
	 */
	public boolean isValidationFailed() {
		return validationFailed;
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		validationFailed = true;
		errors.add(e);
		throw e;
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		validationFailed = true;
		errors.add(e);
		throw e;
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		validationFailed = true;
		errors.add(e);
		throw e;
	}

	public java.util.List<SAXParseException> getErrors() {
		return errors;
	}

	public void printErrors() {
		if (validationFailed) {
			System.out.println("XML validation errors:");
			for (SAXParseException e : errors) {
				System.out.printf("Error:%n%s%n", e.getMessage());
			}
		} else {
			System.out.println("XML is valid!");
		}
	}
}
