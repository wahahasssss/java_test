
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JpypeDemo {
    public String DemoTest() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File("E:/add.txt"));
        outputStream.write("测试测试葡萄红人".getBytes());
        outputStream.close();
        return "Hello World";
    }
}
