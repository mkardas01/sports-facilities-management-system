package put.poznan.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class  SportApplication {

	public static void main(String[] args) {

		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.load();

		// Set environment variables programmatically
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(SportApplication.class, args);
	}

}
