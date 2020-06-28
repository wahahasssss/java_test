package com.hdu.jdbc.servicloader;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 下午2:14
 */
public class ServiceLoaderMain {
    public static void main(String[] args){
        ServiceLoader<MessageService> serviceLoader = ServiceLoader.load(MessageService.class);
        Iterator<MessageService> services = serviceLoader.iterator();
        while (services.hasNext()){
            MessageService messageService = services.next();
            System.out.println(messageService.getMessage());
        }
    }
}
