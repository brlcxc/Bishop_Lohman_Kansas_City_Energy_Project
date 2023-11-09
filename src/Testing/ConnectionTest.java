package Testing;

import Logic.SQLConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionTest {
    SQLConnection sqlConnection;
    @Before
    public void setUp() throws Exception{
        sqlConnection = new SQLConnection();
    }

    @Test
    public void testEstablishingConnection() {
        assertTrue("Connection Failed", sqlConnection.EstablishConnection("root", "123Password"));
    }
}