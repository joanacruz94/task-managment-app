package com.ironhack.notificationclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationClientApplication.class, args);
	}
}
