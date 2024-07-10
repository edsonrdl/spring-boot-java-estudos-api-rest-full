package com.api.todolist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListApplication  {
	private static final Logger logger = LoggerFactory.getLogger(ToDoListApplication .class);

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication .class, args);
		System.out.print("Iniciado!");
	}

	public void doSomething() {
        logger.error("Erro ao iniciar projeto.");
    }

}
