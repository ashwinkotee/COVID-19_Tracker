package testing.coronavirustracker.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import testing.coronavirustracker.models.LocationStats;
import testing.coronavirustracker.database.DataBase;
import testing.coronavirustracker.database.ImportDataDTO;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/";
    //private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/ntvuong/testingurl/main/test.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        var date = sdf.format(new Date());
        //String todaysUrl = VIRUS_DATA_URL + date.toString() + ".csv";
        //I gave todaysUrl the exact date in order to test if the data is inserted into the DB
        String todaysUrl = VIRUS_DATA_URL + "11-16-2020.csv";
        //String todaysUrl = VIRUS_DATA_URL;

        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(todaysUrl))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(httpResponse.body());

        //try{
            DataBase db = new DataBase();
            db.createConnection();
            db.setupTables();
            //db.setupTestTable();
        try{
            StringReader csvBodyReader = new StringReader(httpResponse.body());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
            for (CSVRecord record : records) {

                ImportDataDTO dto = new ImportDataDTO();
                ImportDataDTO dto1 = new ImportDataDTO();

                dto.setFips(record.get("FIPS"));
                System.out.println("FIPS test:" + record.get("FIPS"));
                dto.setAdmin(record.get("Admin2"));
                dto.setProvinceState(record.get("Province_State"));
                dto.setCountryRegion(record.get("Country_Region"));
                dto.setLastUpdate(record.get("Last_Update"));
                dto.setLat(record.get("Lat"));
                dto.setLongVal(record.get("Long_"));
                dto.setConfirmed(record.get("Confirmed"));
                dto.setDeaths(record.get("Deaths"));
                dto.setRecovered(record.get("Recovered"));
                dto.setActive(record.get("Active"));
                dto.setCombinedKey(record.get("Combined_Key"));
                dto.setIncidentRate(record.get("Incident_Rate"));
                dto.setCaseFatalityRatio(record.get("Case_Fatality_Ratio"));

                //dto1.setFips(record.get("FIPS"));
                //dto1.setCountryRegion(record.get("Country_Region"));
                db.insertData(dto);
                //db.selectData(dto1);

                //db.insertData(dto);

                /*LocationStats locationStats = new LocationStats();
                locationStats.setState(record.get("Province_State"));
                locationStats.setCountry(record.get("Country_Region"));
                //locationStats.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
                locationStats.setLatestTotalCases(Integer.parseInt(record.get("Confirmed")));
                //System.out.println(locationStats);
                newStats.add(locationStats);*/

            }
            //db.fetchData("US");
            //db.shutdown();
            this.allStats = newStats;
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
        //db.fetchData();
        db.shutdown();
    }
}
