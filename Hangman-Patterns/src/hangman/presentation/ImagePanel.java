package hangman.presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final String HANGMAN_IMAGE_PATH = "images/hm12.gif";
	/**
	 * Panel and logo image width are equal.
	 */
	public static final int IMAGE_WIDTH = 300;
	/**
	 * Logo panel and logo image height are equal.
	 */
	public static final int IMAGE_HEIGHT = 200;
	/**
	 * Stores image objet to be drawn.
	 */
	private Image image;

	private boolean visibleImage;
	/**
	 * For strech purposes.
	 */
	private BufferedImage bufferedImage; // @jve:decl-index=0:
	private MediaTracker mediaTracker;

	/**
	 * This is the default constructor
	 */
	public ImagePanel() {
		super();
		initialize();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	/**
	 * Initializes all components.
	 */
	private void initialize() {
		setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
		this.visibleImage = false;
	}

	public void drawImage() {
		/**
		 * Path who is related to the LogoPanel class location.
		 */
		URL imgUrl = getClass().getResource(HANGMAN_IMAGE_PATH);
		if (imgUrl == null) {
			JOptionPane.showMessageDialog(this,
					"Cannot find path to logo image.", "Error Loading Logo",
					JOptionPane.ERROR_MESSAGE);
		}
		image = Toolkit.getDefaultToolkit().getImage(imgUrl);
		mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForID(0);
		} catch (java.lang.InterruptedException interruptedexception) {
			JOptionPane.showMessageDialog(this, "Image loading interrupted",
					"Error Loading Logo", JOptionPane.ERROR_MESSAGE);
		}
		if (mediaTracker.isErrorID(0)) {
			JOptionPane.showMessageDialog(this, "Error loading image",
					"Error Loading Logo", JOptionPane.ERROR_MESSAGE);
		}
		bufferedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		bufferedImage.createGraphics().drawImage(image, 0, 0, IMAGE_WIDTH,
				IMAGE_HEIGHT, this);
		this.visibleImage = true;
	}

	public boolean isVisibleImage() {
		return visibleImage;
	}

	public void setVisibleImage(boolean visibleImage) {
		this.visibleImage = visibleImage;
	}

	/**
	 * Draws image on resizing the panel.
	 * 
	 * @param g
	 *            Logo panel's graphics.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.visibleImage == true) {
			Graphics2D graphics2d = (Graphics2D) g;
			graphics2d.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(),
					this);
		}
	}

}
