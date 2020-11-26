package testing.coronavirustracker.database;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;
import testing.coronavirustracker.services.CoronaVirusDataService;
// if intellij is crying about missing jars, manually import derby.jar, derbyclient.jar, derbynet.jar, derbytools.jar
// at file->project structure->modules->dependencies
public class DataBase {
    private static String dbURL = "jdbc:derby://localhost:1527/CovidTrackerDB;create=true;";
    private static String tableName = "globalStatistics";
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args)
    {
        // run startNetworkServer -h 0.0.0.0 from derby/bin before attempting to connect!
        //createConnection();
        //shutdown();
        //fetchData("US");
    }

    public static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }

    public static void insertData(ImportDataDTO dto)
    {
        try
        {
            System.out.println("fips: " + dto.getFips());
            System.out.println("admin: " + dto.getAdmin());
            System.out.println("country: " + dto.getCountryRegion());
            System.out.println("date: " + dto.getLastUpdate());
            //int i = 0;
            stmt = conn.createStatement();
            String sql = "INSERT INTO import_data (FIPS, Admin2, Province_State, Country_Region, Last_Update, Lat, Long, Confirmed, Deaths, Recovered, Active, Combined_Key, Incident_Rate, Case_Fatality_Ratio) VALUES('"
                             + dto.getFips() + "',' "
                             //+ "30000',' "
                             + dto.getAdmin() + "',' "
                             + dto.getProvinceState() + "',' "
                             + dto.getCountryRegion() + "',' "
                             + dto.getLastUpdate() + "',' "
                             //+ "2020-11-17" + "', '"
                             + dto.getLat() + "',' "
                             + dto.getLongVal() + "',' "
                             //+ i + "',' "
                             + dto.getConfirmed() + "',' "
                             //+ i + "',' "
                             + dto.getDeaths() + "',' "
                             //+ i + "',' "
                             + dto.getRecovered() + "',' "
                             //+ i + "',' "
                             + dto.getActive() + "',' "
                             + dto.getCombinedKey() + "',' "
                             + dto.getIncidentRate() + "',' "
                             + dto.getCaseFatalityRatio() + "')";
//
            stmt.executeUpdate(sql);
//
            stmt.close();
        }

        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

    public static void fetchData(String countryName)
    {
        try {
            stmt = conn.createStatement();
            String statement = "select * from import_data where Country_Region = ' " + countryName +"'";
            ResultSet results = stmt.executeQuery(statement);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            List<JSONObject> objects = new ArrayList<>();
            JSONArray listArr = new JSONArray();
            for (int i=1; i<=numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            //if (results.next()) {
            while (results.next()) {
                JSONObject jsonObject = new JSONObject();
                JSONObject dataObject = new JSONObject();
                int id = results.getInt(1);
                int fips = results.getInt(2);
                //jsonObject.put(rsmd.getColumnName(2), fips);
                String admin = results.getString(3);
                //jsonObject.put(rsmd.getCatalogName(3), admin);
                String province_state = results.getString(4).trim();
                //jsonObject.put(rsmd.getColumnName(4), province_state);
                String country = results.getString(5).trim();
                //jsonObject.put(rsmd.getColumnName(5), country);
                Date date = results.getDate(6);
                //jsonObject.put(rsmd.getCatalogName(6), date);
                double lat = results.getDouble(7);
                //jsonObject.put(rsmd.getCatalogName(7), lat);
                double long_ = results.getDouble(8);
                //jsonObject.put(rsmd.getCatalogName(8), long_);
                int confirmed = Integer.parseInt(results.getString(9).trim());
                jsonObject.put(rsmd.getColumnName(9), confirmed);
                int deaths = Integer.parseInt(results.getString(10).trim());
                jsonObject.put(rsmd.getColumnName(10), deaths);
                int recovered = Integer.parseInt(results.getString(11).trim());
                jsonObject.put(rsmd.getColumnName(11), recovered);
                int active = Integer.parseInt(results.getString(12).trim());
                jsonObject.put(rsmd.getColumnName(12), active);
                String combinedKey = results.getString(13);
                //jsonObject.put(rsmd.getCatalogName(13), combinedKey);
                double incidentRate = results.getDouble(14);
                //jsonObject.put(rsmd.getCatalogName(14), incidentRate);
                double caseFatal = results.getDouble(15);
                //jsonObject.put(rsmd.getColumnName(15), caseFatal);
                objects.add(jsonObject);
                dataObject.put(province_state, jsonObject);
                listArr.put(dataObject);
                try (FileWriter file = new FileWriter("covid_data.json")) {

                    file.write(listArr.toString());
                    file.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(id + "\t\t" + fips + "\t\t" + admin + "\t\t" +
                        province_state + "\t\t" + country + "\t\t" +
                        date + "\t\t" + lat + "\t\t" + long_ + "\t\t" +
                        confirmed + "\t\t" + deaths + "\t\t" + recovered + "\t\t" +
                        active + "\t\t" + combinedKey + "\t\t" + incidentRate + "\t\t" +
                        caseFatal);// + "\t\t" + admin + "\t\t" + province_state + "\t\t" + country);
            }
            results.close();

//            for (int i = 0; i < objects.size(); i++)
//            {
//                System.out.println(objects.get(i));
//            }
//            System.out.println(objects);
            System.out.println(listArr);
            stmt.close();
            //}
            //else {
            //    System.out.println("results is empty");
            //}
        }
        catch (SQLException sqlExcept){
            sqlExcept.printStackTrace();
        }
    }

    public static void setupTables()
    {
      try
        {
            stmt = conn.createStatement();
            String dropTable = "DROP TABLE import_data";
            //IMPORTANT NOTE!!! WHILE RUNNING THE PROGRAM, IF ERROR 'IMPORT_DATA DOES NOT EXIST' THEN COMMENT
            //THE LINE 'stmt.executeUpdate(dropTable)' BELOW. AFTER COMMENTING AND RUNNING THE PROGRAM,
            //YOU NEED TO UNCOMMENT IT.
            //THE REASON WHY WE NEED DROPTABLE HERE IS BECAUSE WE ALWAYS CREATE NEW TABLE, NOT UPDATE IT.
            stmt.executeUpdate(dropTable);
            //stmt = conn.createStatement();

            //THE DATABASE HAS SOME PROBLEMS WITH INTEGER AND FLOAT, SO ALL OF THE COLUMNS WITH INTEGER AND FLOAT TYPE
            //HAS BEEN TURNED INTO VARCHAR. SO IT IS IMPORTANT TO CHANGE IT BACK INTO INTEGER/FLOAT WHILE
            //QUERYING THE DATA. I DID IT IN FETCHDATA() FUNCTION ABOVE.
            String sql = "CREATE TABLE import_data\n" +
                            "(ID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                            //"('ID INTEGER PRIMARY KEY NOT NULL GENERATED BY DEFAULT AS IDENTITY ('START WITH 1',' INCREMENT BY 1'),\n" +
                            "FIPS VARCHAR(500),\n" +
                            "Admin2 VARCHAR(500),\n" +
                            "Province_State VARCHAR(500),\n" +
                            "Country_Region VARCHAR(500),\n" +
                            "Last_Update VARCHAR(500),\n" +
                            "Lat VARCHAR(500),\n" +
                            "Long VARCHAR(500),\n" +
                            "Confirmed VARCHAR(500),\n" +
                            "Deaths VARCHAR(500),\n" +
                            "Recovered VARCHAR(500),\n" +
                            "Active VARCHAR(500),\n" +
                            "Combined_Key VARCHAR(500),\n" +
                            "Incident_Rate VARCHAR(500),\n" +
                            "Case_Fatality_Ratio VARCHAR(500),\n" +
                            "PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            stmt.close();
        }

        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

    public static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        }
        catch (SQLException sqlExcept)
        {

        }

    }
}
