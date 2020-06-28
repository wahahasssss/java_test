package com.hdu.javserializable;

import com.hdu.model.Students;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/19
 * @Time 下午5:33
 */
public class OriginalSerializable {
    static String path = "/Users/shushoufu/Desktop/document/java_test/kryo/src/main/resources/file.bin";
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        setSerializableObject();
        System.out.println("java 序列化时间："+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        getSerializableObject();
        System.out.println("java原生反序列化时间："+(System.currentTimeMillis() - start));
    }

    public static void setSerializableObject() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream so = new ObjectOutputStream(fileOutputStream);
        for (int i = 0;i<100000000;i++){
            so.writeObject(new Students("zhang"+i,i));
        }
        so.flush();
        so.close();
    }

    public static void getSerializableObject(){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Students students = null;
            while ((students =(Students)objectInputStream.readObject())!=null){
//                System.out.println(students.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
