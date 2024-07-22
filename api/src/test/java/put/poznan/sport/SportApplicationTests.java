package put.poznan.sport;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import put.poznan.sport.controller.CoachController;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.service.CoachImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoachController.class)
@AutoConfigureMockMvc
public class SportApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CoachImpl coachImpl;

	@Test
	public void testGetCoachById() throws Exception {
		Coach coach = new Coach();
		coach.setId(1);
		coach.setName("John");
		coach.setSurname("Doe");

		when(coachImpl.findById(1)).thenReturn(Optional.of(coach));

		mockMvc.perform(get("/coaches/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("John"))
				.andExpect(jsonPath("$.surname").value("Doe"));
	}

	@Test
	public void testCreateCoach() throws Exception {
		Coach coach = new Coach();
		coach.setName("Alice");
		coach.setSurname("Smith");

		when(coachImpl.save(any(Coach.class))).thenReturn(coach);

		mockMvc.perform(post("/coaches")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(coach)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Alice"))
				.andExpect(jsonPath("$.surname").value("Smith"));
	}




}
