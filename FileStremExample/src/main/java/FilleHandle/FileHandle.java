package FilleHandle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.IntPredicate;

public class FileHandle {
    private File file;
	public FileHandle(String filepath)
    {
		this.file = new File(filepath);
    }
	public void CreateFile() {
		if (this.file.exists()) {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean IsExists() {
		return	 file.exists();
	}
	public String Read() {
		try {
		    FileInputStream inputStream = new FileInputStream(file);
		    byte[] byt = new byte[1024];
		    
		    int len = inputStream.read(byt);
		    String reultString = new String(byt,0,len);
		    inputStream.close();
		    return reultString;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public void Write(String content) {
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			byte[] cont = content.getBytes();
			outputStream.write(cont);
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
