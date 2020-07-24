package com.ironhack.projectclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProjectClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectClientApplication.class, args);
	}
}
