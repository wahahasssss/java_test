package com.hdu.kyroserializable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hdu.model.Students;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/19
 * @Time 下午5:46
 */
public class KyroSerializable {
    static String path = "/Users/shushoufu/Desktop/document/java_test/kryo/src/main/resources/kryo.bin";

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        setSerializableObject();
        System.out.println("kryo 序列化时间：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        getSerializableObject();
        System.out.println("kryo 原生反序列化时间：" + (System.currentTimeMillis() - start));
    }

    public static void setSerializableObject() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.register(Students.class);
        Output output = new Output(new FileOutputStream(path));
        for (int i = 0; i < 100000000; i++) {
            kryo.writeObject(output, new Students("zhang" + i, i));
        }
        output.flush();
        output.close();
    }

    public static void getSerializableObject() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        Input input;
        try {
            input = new Input(new FileInputStream(path));
            Students students = null;
            while ((students = kryo.readObject(input, Students.class)) != null) {
//                System.out.println(students.toString());
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
