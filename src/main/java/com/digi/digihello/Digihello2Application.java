package com.digi.digihello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.digi.digihello.repository")
public class Digihello2Application {

	public static void main(String[] args) {
		SpringApplication.run(Digihello2Application.class, args);
		
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1.toString());
		System.out.println(s2.toString());
	}

}
