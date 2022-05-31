package Testing;

import Foundation.Database.DatabaseConnection;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class Testing {
    public static DatabaseConnection instance; // Refers to the original (production) code


    @Before
    public void setUp() {
        System.out.println("Before ...");
        instance = new DatabaseConnection();
    }

    @After
    public void tearDown() {
        System.out.println("After ...");
        instance = null;
    }

    @Test
    public void testGetConnection() throws SQLException {
        Connection con = DatabaseConnection.connect();
        Assert.assertNotNull(con);
        con.close();
    }


} // end of class