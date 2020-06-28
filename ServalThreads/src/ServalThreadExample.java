import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServalThreadExample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		  FileOutputStream outputStream = new FileOutputStream(new File("E:/add.txt"));
    	  outputStream.write("测试测试葡萄红人".getBytes());
    	  outputStream.close();
	}

}
