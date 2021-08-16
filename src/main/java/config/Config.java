package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";

    private final static Properties properties = new Properties();

    public synchronized static String getProperty(String name) {
        if(properties.isEmpty()) {
            try (InputStream is = new FileInputStream("src/dao.properties")){

                properties.load(is);

            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        return properties.getProperty(name);
    }
}
