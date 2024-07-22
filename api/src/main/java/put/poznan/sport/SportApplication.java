package put.poznan.sport;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.service.CoachImpl;

import java.util.List;

@SpringBootApplication
public class SportApplication {

	public static void main(String[] args) {
		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.load();

		// Set environment variables programmatically
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		ApplicationContext context = SpringApplication.run(SportApplication.class, args);

		// Example: Get CoachService bean from context
		CoachImpl coachImpl = context.getBean(CoachImpl.class);

		// Example: Test CoachService methods
		testCoachService(coachImpl);

		// Example: Test CoachController (assuming it's a RESTful controller)
		// Make HTTP requests to test endpoints

		// Shutdown Spring context
		SpringApplication.exit(context);
	}

	private static void testCoachService(CoachImpl coachImpl) {
		// Example: Add a coach
		Coach coach = new Coach();
		coach.setName("John");
		coach.setSurname("Doe");

		coachImpl.save(coach);
		System.out.println("Coach saved with ID: " + coach.getId());

		// Example: Retrieve the coach by ID
		List<Coach> allCoaches = coachImpl.findAll();
		System.out.println("All Coaches:");
		for (Coach c : allCoaches) {
			System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + ", Surname: " + c.getSurname());
		}
		// Example: Delete the coach
		coachImpl.deleteById(coach.getId());
		System.out.println("Coach deleted with ID: " + coach.getId());
		allCoaches = coachImpl.findAll();
		System.out.println("All Coaches:");
		for (Coach c : allCoaches) {
			System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + ", Surname: " + c.getSurname());
		}
	}
}


