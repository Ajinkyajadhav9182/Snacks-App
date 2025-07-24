package Govinda.Snack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GovindaSnacksApplication {

	public static void main(String[] args) {
		SpringApplication.run(GovindaSnacksApplication.class, args);
		System.out.println("✅ Server is running!");
	}
}