package testing.coronavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import testing.coronavirustracker.services.CoronaVirusDataService;

@SpringBootApplication
@EnableScheduling
public class CoronavirusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusTrackerApplication.class, args);
	}

}
