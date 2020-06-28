//package com.hdu.hadoop;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.*;
//import org.apache.hadoop.io.IOUtils;
//import java.net.URI;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//public class HadoopUtil {
//
//	private String url = "";
//	private Configuration conf = null;
//	private String user = "";
//
//    public HadoopUtil() {
//
//    }
//
//    public void init(String hdfsUrl, Configuration conf, String user) throws InterruptedException, IOException{
//
//    	this.conf = conf;
//    	this.user = user;
//    	this.url  = hdfsUrl;
//    }
//
//    /**
//     * 判断路径是否存在
//     *
//     * @param path
//     * @return
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public  boolean exists(String path) throws InterruptedException, IOException {
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//        boolean isexist = fs.exists(new Path(path));
//        fs.close();
//        return isexist;
//    }
//
//    /**
//     * 创建文件
//     *
//     * @param filePath
//     * @param contents
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public void createFile(String filePath, String contents) throws IOException, InterruptedException {
//        Path path = new Path(filePath);
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//        FSDataOutputStream outputStream = fs.create(path);
//        outputStream.writeBytes(contents);
//        outputStream.close();
//        fs.close();
//    }
//
//    public void appendFile(String filePath, String contents) throws IOException, InterruptedException{
//
//        String file = readFile(filePath);
//        deleteFile(filePath);
//        createFile(filePath, file + contents);
//    }
//
//    /**
//     * @param localFilePath
//     * @param remoteFilePath
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public void copyFromLocalFile(String localFilePath, String remoteFilePath) throws IOException, InterruptedException {
//        Path localPath = new Path(localFilePath);
//        Path remotePath = new Path(remoteFilePath);
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//        fs.copyFromLocalFile(true, true, localPath, remotePath);
//        fs.close();
//    }
//
//    /**
//     * 删除目录或文件
//     *
//     * @param remoteFilePath
//     * @return
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public boolean deleteFile(String remoteFilePath) throws IOException, InterruptedException {
//
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//    	boolean result = false;
//    	try{
//    		 result = fs.delete(new Path(remoteFilePath), true);
//    	}catch(IOException e){
//    		e.printStackTrace();
//    	}
//        fs.close();
//        return result;
//    }
//
//    /**
//     * 文件重命名
//     *
//     * @param oldFileName
//     * @param newFileName
//     * @return
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public boolean renameFile(String oldFileName, String newFileName) throws IOException, InterruptedException {
//        Path oldPath = new Path(oldFileName);
//        Path newPath = new Path(newFileName);
//
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//        boolean result = fs.rename(oldPath, newPath);
//        fs.close();
//        return result;
//    }
//
//    /**
//     * 创建目录
//     *
//     * @param dirName
//     * @return
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public  boolean createDirectory(String dirName) throws IOException, InterruptedException {
//        Path dir = new Path(dirName);
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//        boolean result = fs.mkdirs(dir);
//        fs.close();
//        return result;
//    }
//
////    /**
////     * 列出指定路径下的所有文件(不包含目录)
////     *
////     * @param basePath
////     * @param recursive
////     * @throws InterruptedException
////     */
////    public RemoteIterator<LocatedFileStatus> listFiles(String basePath, boolean recursive) throws IOException, InterruptedException {
////
////    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
////        RemoteIterator<LocatedFileStatus> fileStatusRemoteIterator = fs.list lis(new Path(basePath), recursive);
////        return fileStatusRemoteIterator;
////    }
//
//
//    /**
//     * 列出指定目录下的文件\子目录信息（非递归）
//     *
//     * @param dirPath
//     * @return
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public FileStatus[] listStatus(String dirPath) throws IOException, InterruptedException {
//
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//        FileStatus[] fileStatuses = fs.listStatus(new Path(dirPath));
//        return fileStatuses;
//    }
//
//
//    /**
//     * 读取文件内容
//     *
//     * @param filePath
//     * @return
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public String readFile(String filePath) throws IOException, InterruptedException {
//        String fileContent = null;
//        Path path = new Path(filePath);
//        InputStream inputStream = null;
//        ByteArrayOutputStream outputStream = null;
//
//    	FileSystem fs = FileSystem.get(URI.create(url), conf, user);
//
//        try {
//            inputStream = fs.open(path);
//            outputStream = new ByteArrayOutputStream(inputStream.available());
//            IOUtils.copyBytes(inputStream, outputStream, this.conf);
//            fileContent = outputStream.toString();
//        } finally {
//            IOUtils.closeStream(inputStream);
//            IOUtils.closeStream(outputStream);
//        }
//
//        fs.close();
//        return fileContent;
//    }
//}
