package testing.coronavirustracker;

import org.awaitility.Durations;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import testing.coronavirustracker.controllers.HomeController;
import testing.coronavirustracker.models.LocationStats;
import testing.coronavirustracker.services.CoronaVirusDataService;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;
import static org.springframework.test.web.servlet.request.MockMvcRequestB‌​uilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMat‌​chers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMat‌​chers.status;
import org.springframework.http.MediaType;

@SpringJUnitConfig(CoronavirusTrackerApplication.class)
@SpringBootTest
class CoronavirusTrackerApplicationTests {

	private CoronaVirusDataService coronaVirusDataService;
	private HomeController homeController;

	private MockMvc mockMvc;

	@Test
	void performFetchData_contextCheck() throws IOException, InterruptedException {
		coronaVirusDataService = new CoronaVirusDataService();

		coronaVirusDataService.fetchVirusData();
		List<LocationStats> result = coronaVirusDataService.getAllStats();
		System.out.println(result);
		assertNotNull(result);
		assertEquals(result.get(0).getCountry(), "Afghanistan");
	}
/*
	@Test
	void testCountry() throws Exception {
		List<String> result = this.homeController.country("Ireland");
		System.out.println(result);
		assertNotNull(result);

		this.mockMvc.perform(get("/Ireland")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}

	@Test
	void testCountryDeaths() throws Exception {
		this.mockMvc.perform(get("/Ireland/totalDeaths")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}

	@Test
	void testCountryConfirmed() throws Exception {
		this.mockMvc.perform(get("/Ireland/totalConfirmed")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}

	@Test
	void testCountryRecovered() throws Exception {
		this.mockMvc.perform(get("/Ireland/totalRecovered")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}

	@Test
	void testCountryActive() throws Exception {
		this.mockMvc.perform(get("/Ireland/totalActive")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}

	@Test
	void testDate() throws Exception {
		this.mockMvc.perform(get("/2020-11-12")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}
*/
}
