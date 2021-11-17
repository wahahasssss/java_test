package com.hdu.jdbc;

import com.hdu.jdbc.servicloader.MessageService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 下午6:44
 */
public class ServiceLoaderWithCustomeUrlMain {
    public static void main(String[] args) throws MalformedURLException {
        File file = new File("/Users/shushoufu/Desktop/prest_example-1.0-SNAPSHOT.jar");
        URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
        ServiceLoader serviceLoader = ServiceLoader.load(MessageService.class);
        Iterator<MessageService> services = serviceLoader.iterator();
        while (services.hasNext()) {
            System.out.println(services.next());
        }

    }
}
