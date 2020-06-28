package FilleHandle;
import javax.swing.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class filetest {

	@Test
	public void test() {
		FileHandle handle = new FileHandle("D:/TEXT.txt");
		handle.Write("这是我的小测试");

		String resulString = handle.Read();
	    if (resulString!=null) {
			System.out.println("sucess");
		}else {
			fail("失败");
		}
	    if (handle instanceof FileHandle) {
			System.out.println(".....");
		}
	}

}
