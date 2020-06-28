package com.hdu.leetcodedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class LeetcodeDemoApplication {

	private static void test(){
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		HashMap<String,Object> mapsDetails = new HashMap<>();
		mapsDetails.put("one",1);
		mapsDetails.put("two",2);
		mapsDetails.put("three",3);
		mapsDetails.put("four",4);

		keys.addAll(mapsDetails.keySet());
		values.addAll(mapsDetails.values());
		System.out.println(keys);
		System.out.println("===================");
		System.out.println(values);
	}

	public static void main(String[] args) {
		test();
		SpringApplication.run(LeetcodeDemoApplication.class, args);
	}
}
