package com.hdu.springboothadoop.runner;

import com.hdu.springboothadoop.hbase.User;
import com.hdu.springboothadoop.hbase.UserRepository;
import com.hdu.springboothadoop.hbase.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/13
 * @Time 下午4:41
 */
//@Component
public class HbaseRunner implements CommandLineRunner {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("begin hbase runner...");
        userUtils.initialize();
        userUtils.addUsers();
        List<User> users = userRepository.findAll();
        System.out.println("Number of users = " + users.size());
        System.out.println(users);
    }
}
