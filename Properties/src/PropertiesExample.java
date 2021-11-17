import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesExample {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = PropertiesExample.class.getResourceAsStream("config.properties");
        InputStream fileInputStream = new FileInputStream(new File("src/config.properties"));
        Properties properties = new Properties();
        properties.load(inputStream);
        String keyString = (String) properties.getProperty("name");
        System.out.println(keyString);
    }
}
