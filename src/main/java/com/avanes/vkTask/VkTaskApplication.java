package com.avanes.vkTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VkTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkTaskApplication.class, args);
	}

}
