package testing.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import testing.coronavirustracker.models.LocationStats;
import testing.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }

    @GetMapping("/home/{country}}")
    @ResponseBody
    public List<String> country(@PathVariable String country) {
        List<String> allStats = coronaVirusDataService.getData();
        List<String> countryStats = allStats.stream().filter(stat -> stat.equals(country))
                                    .collect(Collectors.toCollection(ArrayList::new));

        return countryStats;
    }

    @GetMapping("/home/{country}/totalDeaths}")
    @ResponseBody
    public int countryDeaths(@PathVariable String  country) {
        List<String> allStats = coronaVirusDataService.getData();

        List<String> countryStats = allStats.stream().filter(stat -> stat.equals(country))
                                    .collect(Collectors.toCollection(ArrayList::new));
        List<String> tmp = countryStats.stream().map(stat -> stat.split(",")[8])
                           .collect(Collectors.toCollection(ArrayList::new));
        int totalDeaths = tmp.stream().mapToInt(Integer::parseInt).sum();

        return totalDeaths;
    }

    @GetMapping("/home/{country}/totalConfirmed}")
    @ResponseBody
    public int countryConfirmed(@PathVariable String country) {
        List<String> allStats = coronaVirusDataService.getData();
        List<String> countryStats = allStats.stream().filter(stat -> stat.equals(country))
                                    .collect(Collectors.toCollection(ArrayList::new));
        List<String> tmp = countryStats.stream().map(stat -> stat.split(",")[7])
                .collect(Collectors.toCollection(ArrayList::new));
        int totalConfirmed = tmp.stream().mapToInt(Integer::parseInt).sum();

        return totalConfirmed;
    }

    @GetMapping("/home/{country}/totalRecovered}")
    @ResponseBody
    public int countryRecovered(@PathVariable String country) {
        List<String> allStats = coronaVirusDataService.getData();
        List<String> countryStats = allStats.stream().filter(stat -> stat.equals(country))
                                    .collect(Collectors.toCollection(ArrayList::new));
        List<String> tmp = countryStats.stream().map(stat -> stat.split(",")[9])
                           .collect(Collectors.toCollection(ArrayList::new));
        int totalRecovered = tmp.stream().mapToInt(Integer::parseInt).sum();

        return totalRecovered;
    }

    @GetMapping("/home/{country}/totalActive}")
    @ResponseBody
    public int countryActive(@PathVariable String country) {
        List<String> allStats = coronaVirusDataService.getData();

        List<String> countryStats = allStats.stream().filter(stat -> stat.equals(country))
                                    .collect(Collectors.toCollection(ArrayList::new));
        List<String> tmp = countryStats.stream().map(stat -> stat.split(",")[10])
                .collect(Collectors.toCollection(ArrayList::new));
        int totalActive = tmp.stream().mapToInt(Integer::parseInt).sum();

        return totalActive;
    }

    @GetMapping("/home/{date}}")
    @ResponseBody
    public List<String> date(@PathVariable String date) {
        List<String> allStats = coronaVirusDataService.getData();
        List<String> countryStats = allStats.stream().filter(stat -> stat.equals(date))
                              .collect(Collectors.toCollection(ArrayList::new));

        return countryStats;
    }

}
