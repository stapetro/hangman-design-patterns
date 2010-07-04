package hangman.utils;

import hangman.constants.HangmanConstants;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class FileUtility {

	/**
	 * Gets text file content.
	 * 
	 * @param filePath
	 *            Absolute or relative path to file.
	 * @return Text content that is read from file, or null - if error occurs.
	 */
	public static String getFileContent(String filePath) {
		FileInputStream fis = null;
		InputStreamReader inputStreamReader = null;
		try {
			StringBuilder content = new StringBuilder();
			char[] cbuf = new char[HangmanConstants.CHARACTER_BUFFER_SIZE];
			fis = new FileInputStream(new File(filePath));
			inputStreamReader = new InputStreamReader(fis, "UTF-8");
			int readBytes = inputStreamReader.read(cbuf, 0,
					HangmanConstants.CHARACTER_BUFFER_SIZE);
			while (readBytes != -1) {
				content.append(cbuf, 0, readBytes);
				readBytes = inputStreamReader.read(cbuf, 0,
						HangmanConstants.CHARACTER_BUFFER_SIZE);
			}
			return content.toString();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			close(fis);
			close(inputStreamReader);
		}
		return null;
	}

	/**
	 * Gets file content in bytes in UTF-8 encoding.
	 * 
	 * @param filePath
	 *            File name to be specified.
	 * @param encoding
	 *            Charset encoding to be set.
	 * @return Byte content that is read from file, or null - if error occurs.
	 */
	public static byte[] getFileContentBytes(String filePath, String encoding) {
		String content = getFileContent(filePath);
		try {
			if (content != null) {
				return content.getBytes(encoding);
			}
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Writes XML data to XML file.
	 * 
	 * @param content
	 *            XML data to be specified.
	 * @param encoding
	 *            Charset encoding to be set.
	 * @return True - if text is written to file successfully, false -
	 *         otherwise.
	 */
	public static boolean writeTextContentToFile(String filePath,
			String content, String encoding) {
		FileOutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			outputStream = new FileOutputStream(filePath);
			outputStreamWriter = new OutputStreamWriter(outputStream, encoding);
			outputStreamWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			close(outputStreamWriter);
			close(outputStream);
		}
		return true;
	}

	public static boolean writeXmlFile(Document document, String filePath) {
		DOMSource xmlSource = new DOMSource(document);
		File file = new File(filePath);
		StreamResult outputTarget = new StreamResult(file);
		try {
			Transformer xmlTransformer = TransformerFactory.newInstance()
					.newTransformer();
			try {
				xmlTransformer.transform(xmlSource, outputTarget);
				return true;
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Closes closeable object.
	 * 
	 * @param closableOject
	 */
	private static void close(Closeable closableOject) {
		if (closableOject != null) {
			try {
				closableOject.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
