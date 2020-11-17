package testing.coronavirustracker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

// this is a construction are atm but db creation and connecting to it works so the rest shouldn't be too difficult
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
        createConnection();
        shutdown();
    }

    private static void createConnection()
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

    private static void insertData()
    {
      try
        {
            stmt = conn.createStatement();
            String sql = "";

            stmt.executeUpdate(sql);
            stmt.close();
        }

        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

    private static void setupTables()
    {
      try
        {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE import_table\n" +
                            "(id INTEGER not NULL,\n" +
                            "FIPS INTEGER,\n" +
                            "Admin2 VARCHAR(500),\n" +
                            "Province_State VARCHAR(500),\n" +
                            "Country_Region VARCHAR(500),\n" +
                            "Last_Update DATE,\n" +
                            "Lat FLOAT,\n" +
                            "Long FLOAT,\n" +
                            "Confirmed INTEGER,\n" +
                            "Deaths INTEGER,\n" +
                            "Recovered INTEGER,\n" +
                            "Active INTEGER,\n" +
                            "Combined_Key VARCHAR(500),\n" +
                            "Incident_Rate FLOAT,\n" +
                            "Case_Fatality_Ratio FLOAT,\n" +
                            "PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            stmt.close();
        }

        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

    private static void shutdown()
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
