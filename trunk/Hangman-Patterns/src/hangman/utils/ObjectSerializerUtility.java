package hangman.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializerUtility {

	public static String serializeOject(Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(baos);
			objectOutputStream.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.flush();
					objectOutputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (baos != null) {
				try {
					baos.flush();
					baos.close();
					return baos.toString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static Object deserializeObject(String serializedContent) {
		byte[] bytes = serializedContent.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream objIn = null;
		try {
			objIn = new ObjectInputStream(bais);
			try {
				return objIn.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (objIn != null) {
				try {
					objIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
