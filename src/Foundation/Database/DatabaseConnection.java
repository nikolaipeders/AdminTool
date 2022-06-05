package Foundation.Database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class DatabaseConnection
{
    private static Connection con = null;
    private static String host;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;

    static
    {
        handleDBProperties();
    }

    public static void handleDBProperties()
    {
        Properties props = new Properties();
        String fileName = "db.properties";
        InputStream input;
        try
        {
            input = new FileInputStream(fileName);
            props.load(input);
            host = props.getProperty("host", "51.38.112.206");
            port = props.getProperty("port","1433");
            databaseName = props.getProperty("databaseName");
            userName=props.getProperty("userName", "sa");
            password=props.getProperty("password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(Exception e)
        {
            System.err.println(e.toString());
        }
    }

    public static Connection connect()
    {
        try
        {
            if (con == null)
            {
                con = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + databaseName, userName, password);
            }
            else
            {
                System.out.println("DB already connected");
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.toString());
        }
        return con;
    }

    public static void disconnect(){
        try
        {
            con.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.toString());
        }
    }
}