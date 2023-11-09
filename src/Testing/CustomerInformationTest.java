package Testing;

import Logic.SQLConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CustomerInformationTest {
    SQLConnection sqlConnection;
    @Before
    public void setUp() throws Exception{
        sqlConnection = new SQLConnection();
        sqlConnection.EstablishConnection("root", "123Password");
    }

    @Test
    public void testCreatingCustomer() {
        String query = "UPDATE Customer SET " +
                "CustomerFirstName = 'Bishop" +
                "', CustomerLastName = 'Lohman" +
                "', Email = 'lohmanbishop@gmail.com" +
                "', PhoneNumber = '816-889-8855" +
                "', Address = '800 Drive" +
                "', City = 'KC" +
                "', State = 'MO" +
                "', Zip = '64284" +
                "', EnergyTariff = 17" +
                ", MeterType = 'Dial" +
                "', TotalDue = 1" +
                ", RemainingBalance = 1" +
                ", Paid = 0" +
                " WHERE CustomerID = 123";

        sqlConnection.setCustomerID("123");
        assertTrue("Failed to create customer", sqlConnection.UpdateCustomer(query));
    }
    @After
    public void tearDown( ) {
        sqlConnection.DeleteCustomer();
    }
}