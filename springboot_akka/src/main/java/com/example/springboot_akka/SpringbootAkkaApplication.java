package com.example.springboot_akka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class SpringbootAkkaApplication {

	public static void main(String[] args) {

		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println(inetAddress.getCanonicalHostName());
			System.out.println("======================");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		SpringApplication.run(SpringbootAkkaApplication.class, args);
	}
}
