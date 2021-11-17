import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import org.apache.hadoop.io.IOUtils;
import org.apache.http.client.protocol.RequestTargetAuthentication;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.protocol.proto.HdfsProtos.SnapshottableDirectoryListingProto;
import org.apache.xerces.impl.xpath.regex.ParseException;
import org.codehaus.jackson.map.DeserializerFactory.Config;
import org.jets3t.service.acl.EmailAddressGrantee;
import org.junit.experimental.theories.Theories;
import org.mortbay.jetty.HttpParser.Input;

import com.sun.istack.Nullable;

public class HadoopE {

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        String url = "hdfs://192.168.56.197:9000";
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fileSystem = FileSystem.get(URI.create(url), configuration, "hadoop");
        boolean isexist = fileSystem.exists(new Path("/shusf"));

        if (isexist) {
            System.out.println("/shusf" + " is exist \n");

        } else {
            if (createDirectory("/shusf", url, configuration, "hadoop")) {
                System.out.println("创建文件夹成功");
            } else {
                System.out.println("创建文件夹失败");
                ;
            }
        }
        boolean isexitsFile = fileSystem.exists(new Path("/shusf/text.txt"));
        fileSystem.close();
        if (isexitsFile) {
            System.out.println("存在文件");
            appendFile("/shusf/text.txt", "这是在新添加内容", url, "hadoop", configuration);
            System.out.println("追加文件内容成功");
        } else {
            createFile("/shusf/text.txt", "分分分分方法反反复复凤飞飞反复反复反复反复反复反复反复", configuration, url, "hadoop");
            System.out.println("创建文件成功");
        }
        System.out.println(readFile("/shusf/text.txt", url, configuration, "hadoop").toString());
        copyFile("D:\\Example.txt", "/", "hadoop", url, configuration);
        createFile("/eeeee.txt", "ce测试中文", configuration, url, "hadoop");
        //rename("/eeeee.txt", "/ee.txt", url, "hadoop", configuration);
        isExistsFile("/ee3.txt", url, "hadoop", configuration);
    }

    //创建文件
    public static void createFile(String filePath, String contents, Configuration conf, String url, String user) throws IOException, InterruptedException {
        Path path = new Path(filePath);
        FileSystem fs = FileSystem.get(URI.create(url), conf, user);
        FSDataOutputStream outputStream = fs.create(path);
        outputStream.write(contents.getBytes(), 0, contents.getBytes().length);
        outputStream.close();
        fs.close();
    }

    public static boolean isExistsFile(String path, String url, String user, Configuration conf) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(URI.create(url), conf, user);
        boolean result = fs.exists(new Path(path));
        System.out.println(path + "该文件" + (result ? "存在" : "不存在"));
        return result;
    }

    /*
     * 拷贝文件
     * */
    public static void copyFile(String srcPath, String desPath, String user, String url, Configuration conf) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(URI.create(url), conf, user);
        fs.copyFromLocalFile(new Path(srcPath), new Path(desPath));
        System.out.println("Upload to" + conf.get("fs.default.name"));

        FileStatus[] files = fs.listStatus(new Path(desPath));
        for (FileStatus file : files) {

            System.out.println(file.getPath());
        }
    }

    /**
     * 创建文件夹
     **/
    public static boolean createDirectory(String dirName, String url, Configuration conf, String user) throws IOException, InterruptedException {
        Path dir = new Path(dirName);
        FileSystem fs = FileSystem.get(URI.create(url), conf, user);
        boolean result = fs.mkdirs(dir);
        fs.close();
        return result;
    }

    /*
     * 重命名文件
     * */
    public static boolean rename(String oldPath, String newPath, String url, String user, Configuration conf) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(URI.create(url), conf, user);
        boolean result = fs.rename(new Path(oldPath), new Path(newPath));
        System.out.println("文件重命名" + (result ? "成功" : "失败"));
        return result;
    }

    /*
     * 追加文件内容
     * */
    public static void appendFile(String filePath, String contents, String url, String user, Configuration conf) throws IOException, InterruptedException {
        String file = readFile(filePath, url, conf, user);
        deleteFile(filePath, url, conf, user);
        createFile(filePath, file + contents, conf, url, user);
    }

    /*
     * 删除文件
     * */
    public static boolean deleteFile(String filepath, String url, Configuration conf, String user) throws IOException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf, user);
        boolean result = false;
        try {
            result = fileSystem.delete(new Path(filepath), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileSystem.close();
        return result;
    }

    /*
     * 读取文件
     * */
    public static String readFile(String filePath, String url, Configuration conf, String user) throws IOException, InterruptedException {
        String fileContents = null;
        Path path = new Path(filePath);
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf, user);

        try {
            inputStream = fileSystem.open(path);
            outputStream = new ByteArrayOutputStream(inputStream.available());
            IOUtils.copyBytes(inputStream, outputStream, conf);
            fileContents = outputStream.toString();
        } catch (Exception e) {

        } finally {
            IOUtils.closeStream(inputStream);
            IOUtils.closeStream(outputStream);
        }
        fileSystem.close();
        return fileContents;
    }
}
