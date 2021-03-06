package com.hdu.springboothadoop.hbase;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserApp {

	private static final Log log = LogFactory.getLog(UserApp.class);

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/application-context.xml", UserApp.class);
		log.info("HBase Application Running");
		context.registerShutdownHook();
		
		UserUtils userUtils = context.getBean(UserUtils.class);		
		userUtils.initialize();
		userUtils.addUsers();
		
		UserRepository userRepository = context.getBean(UserRepository.class);
		List<User> users = userRepository.findAll();
		System.out.println("Number of users = " + users.size());
		System.out.println(users);
	
	}
}
