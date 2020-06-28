package com.hdu.netty.simple_netty.decodeer_encoder;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/22
 * @Time 下午3:23
 */
public class SerializeUtil {
    private static final Integer BUFFER_SIZE = 2048;
    private static final Integer MAX_BUFFER_SIZE = 10485760;


    public <T extends Serializable> byte[] serialization(T obj){
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(),new BeanSerializer(kryo,obj.getClass()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output,obj);
        output.flush();
        output.close();
        byte[] bytes = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return bytes;
    }


    public <T extends Serializable> T deserialization(byte[] bytes,Class clazz){
        try {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.register(clazz,new BeanSerializer(kryo,clazz));
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            Input input = new Input(bais);
            T instance = (T)kryo.readClassAndObject(input);
            return instance;
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("decode is null");
            return null;
        }
    }
}
