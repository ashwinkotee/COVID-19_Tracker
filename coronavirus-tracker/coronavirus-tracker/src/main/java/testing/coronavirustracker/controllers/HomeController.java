package testing.coronavirustracker.controllers;

import org.apache.catalina.connector.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testing.coronavirustracker.database.DataBase;
import testing.coronavirustracker.models.LocationStats;
import testing.coronavirustracker.services.CoronaVirusDataService;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @CrossOrigin
    @RequestMapping("/Global")
    public @ResponseBody String Global(){
        List<LocationStats> allStatsDeaths = coronaVirusDataService.getAllStatsDeaths();
        List<LocationStats> allStatsRecovered = coronaVirusDataService.getAllStatsRecovered();
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalReportedDeaths = allStatsDeaths.stream().mapToInt(stat -> stat.getLatestTotalDeaths()).sum();
        int totalReportedRecovered = allStatsRecovered.stream().mapToInt(stat -> stat.getLatestTotalRecovered()).sum();
        JSONObject globalData = new JSONObject();
        JSONObject data = new JSONObject();
        globalData.put("Confirmed", totalReportedCases);
        globalData.put("Deaths", totalReportedDeaths);
        globalData.put("Recovered", totalReportedRecovered);
        data.put("Global", globalData);
        //model.addAttribute("locationStats", allStats);
        //model.addAttribute("totalReportedCases", totalReportedCases);
//        String confirmed = Integer.toString(totalReportedCases);
//        String deaths = Integer.toString(totalReportedDeaths);
//        String recovered = Integer.toString(totalReportedRecovered);
//        return confirmed + " " + deaths + " " + recovered;
        return data.toString();
    }

    @CrossOrigin
    @RequestMapping("/{country}")
    public @ResponseBody String getCountry(@PathVariable(value = "country") String country) {
        DataBase db = new DataBase();
        db.createConnection();
        return db.fetchData(country).toString();
        //System.out.println("Get mapping country");
        //return country;
    }

    @CrossOrigin
    @RequestMapping("/{date}/{country}")
    public @ResponseBody String getCountryWithDate(@PathVariable(value = "date") String date, @PathVariable(value = "country") String country) throws IOException, InterruptedException {
        CoronaVirusDataService cvds = new CoronaVirusDataService();
        cvds.fetchVirusDataWithDate(date);
        DataBase db = new DataBase();
        db.createConnection();
        return db.fetchData(country).toString();
        //System.out.println("Get mapping country");
        //return country;
    }

    @CrossOrigin
    @RequestMapping("/news")
    public @ResponseBody String getNews(){
        CoronaVirusDataService cvds = new CoronaVirusDataService();
        try {
            return cvds.fetchNews();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ERROR: There are some problems with news";
    }

/*    @GetMapping("/home/{country}/totalDeaths}")
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
*/
}
