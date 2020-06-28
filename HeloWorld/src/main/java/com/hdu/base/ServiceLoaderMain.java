package com.hdu.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 下午7:23
 */
public class ServiceLoaderMain {
    public static void main(String[] args) throws MalformedURLException {
//        File file = new File("/Users/shushoufu/Desktop/jdbc_driver-1.0-SNAPSHOT.jar");
//        URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
//        ServiceLoader serviceLoader = ServiceLoader.load(Driver.class,classLoader);
//        Iterator<Driver> services = serviceLoader.iterator();
//        while (services.hasNext()){
//            System.out.println(services.next());
//        }
        if (false | false){
            System.out.println("qqqq");
        }

    }
}
