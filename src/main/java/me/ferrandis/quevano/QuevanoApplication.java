package me.ferrandis.quevano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class QuevanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuevanoApplication.class, args);
		System.out.println("--------------------------------------");
		System.out.println("Started\nhttp://localhost:8080/webjars/swagger-ui/index.html");
		System.out.println("--------------------------------------");
	}

}
