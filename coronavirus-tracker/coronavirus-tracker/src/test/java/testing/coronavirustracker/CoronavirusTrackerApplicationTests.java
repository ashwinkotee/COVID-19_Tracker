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

@SpringJUnitConfig(CoronavirusTrackerApplication.class)
@SpringBootTest
class CoronavirusTrackerApplicationTests {

	private CoronaVirusDataService coronaVirusDataService;

	@Test
	void performFetchData_contextCheck() throws IOException, InterruptedException {
		coronaVirusDataService = new CoronaVirusDataService();

		coronaVirusDataService.fetchVirusData();
		List<LocationStats> result = coronaVirusDataService.getAllStats();
		System.out.println(result);
		assertNotNull(result);
		assertEquals(result.get(0).getCountry(), "Afghanistan");
	}

}
