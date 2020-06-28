//package com.hdu.hadoop;
//
//import java.io.IOException;
//
//import com.hdu.hadoop.HadoopUtil;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileStatus;
//import org.apache.hadoop.fs.LocatedFileStatus;
//import org.apache.hadoop.fs.RemoteIterator;
//import org.junit.Test;
//
//public class TestFile {
//
//	@Test
//	public void testCreate() throws InterruptedException, IOException {
//
//		String hdfsUrl = "hdfs://192.168.56.197:9000";
//		Configuration con = new Configuration();
//		//con.set("fs.defaultFS", hdfsUrl);
//		con.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
//
//		HadoopUtil ut = new HadoopUtil();
//		ut.init(hdfsUrl, con, "hadoop");
//		if( ut.exists("hi") ){
//			System.out.println("hi"+" is exist \n");
//		}
//		else{
//				if( ut.createDirectory("hi") ){
//					System.out.println("hi"+" create ok\n");
//				}
//				else{
//					System.out.println("hi"+" create failed\n");
//				}
//
//
//		}
//
//		 if(ut.exists("hi/f1.txt"))
//		 {
//				System.out.println("hi/f1.txt"+" exist\n");
//		 }
//		 else
//		 {
//		    	ut.createFile("hi/f1.txt", "love");
//				System.out.println("hi/f1.txt"+" create ok\n");
//		 }
//
//		 if(ut.exists("hi/f2.txt"))
//		 {
//			 System.out.println("hi/f2.txt"+" exist\n");
//		 }
//		 else
//		 {
//		    ut.createFile("hi/f2.txt", "lala");
//			System.out.println("hi/f2.txt"+" create ok\n");
//		 }
//
//		 if( ut.exists("hi/next") ){
//				System.out.println("hi/next"+" is exist \n");
//		 }
//		 else{
//				if( ut.createDirectory("hi/next") ){
//						System.out.println("hi/next"+" create ok\n");
//				}
//				else{
//						System.out.println("hi/next"+" create failed\n");
//				}
//
//
//		 }
//
//		 if(ut.exists("hi/next/ka.txt"))
//		 {
//			System.out.println("hi/next/ka.txt"+" exist\n");
//		 }
//		 else
//		 {
//		    ut.createFile("hi/next/ka.txt", "haha");
//			System.out.println("hi/next/ka.txt" +" create ok\n");
//		 }
//
////		 RemoteIterator<LocatedFileStatus> ll = ut.listFiles("hi",true);
////		 while(ll.hasNext()){
////			 System.out.println(ll.next());
////		 }
////
//		 FileStatus[] st = ut.listStatus("hi");
//		 for(FileStatus t : st){
//			 System.out.println(t);
//		 }
//		 //ut.deleteFile("h1/f1.txt");
//		 //ut.deleteFile("h1/f2.txt");
//		 //ut.deleteFile("h1/next/ka.txt");
//		 //ut.deleteFile("h1/next");
//		 //ut.deleteFile("h1");
//
//		 ut.appendFile("hi/next/ka.txt", "lala");
//
//		 String content = ut.readFile("hi/next/ka.txt");
//		 System.out.println(content);
//	}
//
//}
