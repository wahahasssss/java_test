import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XMLPaser {
	@Test
   public void paserXml() {
	SAXReader reader = new SAXReader();
	try {
		Document doc = reader.read("Config.xml");
		Element root = doc.getRootElement();
		System.out.println(root.attributeValue("name"));
		List<Element> param = root.elements();
		for(Element element:param)
		{
			System.out.println(element.attributeValue("name"));
			if(element.attributeValue("name").equals("a"))
			{
				System.out.println(element.getText());
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
