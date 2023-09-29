package com.api.restful.apirestfulspringbootjava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestfulSpringBootJavaApplication {
	private static final Logger logger = LoggerFactory.getLogger(ApiRestfulSpringBootJavaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiRestfulSpringBootJavaApplication.class, args);
		System.out.print("Olá,mundo");
	}

	public void doSomething() {
        logger.error("Isso é uma mensagem de erro.");
    }

}
