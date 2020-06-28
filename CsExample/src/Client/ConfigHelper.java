package Client;

import java.io.InputStream;
import java.util.Properties;

import javax.jws.WebService;

@WebService
public class ConfigHelper {

	Properties properties = new Properties();
	InputStream inputStream = this.getClass().getResourceAsStream("config.properties");
     
}
