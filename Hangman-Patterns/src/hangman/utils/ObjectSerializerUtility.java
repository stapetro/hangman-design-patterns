package hangman.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

public class ObjectSerializerUtility {

    public static String serializeOject(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos)) {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return baos.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object deserializeObject(String serializedContent) {
        byte[] bytes;
        try {
            bytes = serializedContent.getBytes("UTF-8");
            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                    ObjectInputStream objIn = new ObjectInputStream(bais)) {
                return objIn.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
