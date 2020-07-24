package helper;

import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.util.Properties;

public class Utils {
    public static String readProperty(String filename, String key) {
        Properties properties = new Properties();
//        InputStream inputStream = Object.class.getResourceAsStream( filename);
        try {
//            properties.load(inputStream);
            properties.load(new FileInputStream("src/test/java/conf/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.get(key).toString();
    }
}
