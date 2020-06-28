package com.hdu.base;

import java.util.Scanner;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;


public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           System.out.println("hhhhaa");
           Scanner s = new Scanner(System.in);
           double inputnum = s.nextDouble();
           System.out.println(inputnum);
           int[] grades = new int[]{1,2,3,4,5};
           for(int g:grades)
           {
        	  System.out.println(g);
           }
           Arrays.fill(grades,3,5,99);
           Arrays.sort(grades);
           for(int g:grades)
           {
        	   System.out.println(g);
           }
           int[] newgrades = Arrays.copyOf(grades, 3);
           for(int g:newgrades)
           {
        	   System.out.println(g);
           }
           String str = new String("good");
           System.out.println(str);
           System.out.println(str.indexOf('d'));
           System.out.println(str.charAt(3));
           Date date = new Date();
           System.out.println(String.format("%tH", date));
           System.out.println(String.format("%tM", date));
           String match = "SsS110";
           String regex = "\\p{Upper}\\p{Lower}\\p{Upper}\\d\\d\\d";
           System.out.println(match.matches(regex)?"哈哈哈哈":"嚯嚯嚯嚯");
           StringBuilder strbuild = new StringBuilder("");
           System.out.println(strbuild);
           strbuild.append("poofne");
           System.out.println(strbuild);
           System.out.println(grades.toString()); 
           book b = new book();
           Student stu = new Student();
           System.out.println(stu.GetName());
           LinkedList<String> strs = new LinkedList<String>();
           strs.add("one");
           strs.add("two");
           strs.add("three");
           for(String ss : strs)
           {
        	   System.out.println(ss);
           }
           File file = new File("D:/test.txt");
           if(file.exists())
           {
        	   file.delete();
        	   System.out.println("我已经删除了这sb文件");
           }else {
        	 try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}   
           }
			
		
           
	}

}
