package hangman.logic.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Builder factory Manages XML document tree.
 * 
 * @author Stanislav Petrov
 * 
 */
public class XmlManager {

	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document xmlDocument;
	private ValidationHandler validationHandler;
	private Element root;

	/**
	 * Creates XML manager for specified XML file.
	 * 
	 * @param filePath
	 *            XML file path.
	 * @return Created XML manager or null - if error occurs.
	 */
	public static XmlManager createXmlManager(String filePath) {
		if (filePath != null && filePath.length() > 0) {
			try {
				XmlManager xmlManager = new XmlManager(filePath);
				return xmlManager;
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Document getXmlDocument() {
		return xmlDocument;
	}

	private XmlManager(String filePath) throws ParserConfigurationException,
			SAXException, IOException {
		this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			this.documentBuilder = this.documentBuilderFactory
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw e;
		}
		parseXmlFile(filePath);
	}

	/**
	 * Gets XML node by specifying its attribute name.
	 * 
	 * @param attributeName
	 * @return
	 */
	public Node getNodeByAttribute(String attributeName, String attributeValue) {
		return findNodeByAttribute(this.root, attributeName, attributeValue);
	}

	/**
	 * Gets attribute from the specified XML node.
	 * 
	 * @param xmlNode
	 *            XML node to be specified.
	 * @param attributeName
	 *            Attribute name
	 * @return Attribute if it's found, or null - otherwise.
	 */
	public Node getAttributeByNode(Node xmlNode, String attributeName) {
		return getAttribute(xmlNode, attributeName, null);
	}

	private void parseXmlFile(String filePath) throws SAXException, IOException {
		if (this.documentBuilder != null) {
			this.validationHandler = new ValidationHandler();
			this.documentBuilder.setErrorHandler(this.validationHandler);
			try {
				this.xmlDocument = this.documentBuilder.parse(filePath);
				this.root = this.xmlDocument.getDocumentElement();
			} catch (SAXException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
	}

	/**
	 * Finds XML node by specifying attribute name and value.
	 * 
	 * @param root
	 *            XML node form which recursively traversing starts.
	 * @param attrName
	 *            Attribute name
	 * @param attrValue
	 *            Attribute value
	 * @return XML node if found, or null - otherwise.
	 */
	private Node findNodeByAttribute(Node root, String attrName,
			String attrValue) {
		if (root.hasAttributes() == true) {
			Node attribute = getAttribute(root, attrName, attrValue);
			if (attribute != null) {
				return root;
			}
		}
		if (root.hasChildNodes() == true) {
			NodeList children = root.getChildNodes();
			int size = children.getLength();
			Node foundNode = null;
			Node currentNode = null;
			for (int index = 0; index < size; index++) {
				currentNode = children.item(index);
				if (currentNode.getNodeType() == Element.ELEMENT_NODE) {
					foundNode = findNodeByAttribute(currentNode, attrName,
							attrValue);
					if (foundNode != null) {
						return foundNode;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets attribute by specifying attribute name and value for the
	 * corresponding XML node. Precondition: XML node should have attributes.
	 * 
	 * @param xmlNode
	 * @param attributeName
	 *            Attribute name
	 * @param attributeValue
	 *            Attribute value. If null is specified, it searches only by
	 *            attribute name and gets the first found attribute.
	 * @return Null - if attribute is not found.
	 */
	private Node getAttribute(Node xmlNode, String attributeName,
			String attributeValue) {
		NamedNodeMap attributes = xmlNode.getAttributes();
		int size = attributes.getLength();
		Node currentAttribute = null;
		String attrName = null;
		String attrValue = null;
		for (int index = 0; index < size; index++) {
			currentAttribute = attributes.item(index);
			attrName = currentAttribute.getNodeName();
			attrValue = currentAttribute.getNodeValue();
			if (attrName.equals(attributeName) == true) {
				if (attributeValue == null || attributeValue.equals(attrValue)) {
					return currentAttribute;
				}
			}
		}
		return null;
	}

}
