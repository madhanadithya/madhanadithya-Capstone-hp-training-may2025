package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;

    public static Properties initProp() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
            prop.load(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
    
}
