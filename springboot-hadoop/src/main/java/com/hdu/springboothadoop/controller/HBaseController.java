package com.hdu.springboothadoop.controller;

import com.hdu.springboothadoop.hbase.User;
import com.hdu.springboothadoop.hbase.UserRepository;
import com.hdu.springboothadoop.hbase.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/13
 * @Time 下午4:49
 */
@RestController
public class HBaseController {
    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> listUsers(){
        return userRepository.findAll();
    }

}
