package Logic;

import java.sql.*;
import java.util.ArrayList;

//this panel contains important information regarding the connection, user, and customer
public class SQLConnection {
    final String DatabaseURL = "jdbc:mysql://localhost:3306/customerdatadb";
    private Statement statement;
    private String customerID;
    public SQLConnection(){}
    public boolean EstablishConnection(String usernameInput, String passwordInput){
        try {
            Connection connection = DriverManager.getConnection(DatabaseURL, usernameInput, passwordInput);
            statement = connection.createStatement();
            System.out.println("connected");
            return true;
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return false;
        }
    }
    public void DeleteCustomer(){
        try {
            String deleteStatement = "DELETE FROM Customer " + "WHERE CustomerID = '" + customerID + "'";

            statement.executeUpdate(deleteStatement);
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    public boolean ValidateCustomerID(String userInput){
        String customerStatement = "SELECT CustomerFirstName FROM Customer WHERE CustomerID = " + userInput;
        try {
            ResultSet result = statement.executeQuery(customerStatement);
            if (!result.isBeforeFirst() ) {
                System.out.println("No data");
                throw new RuntimeException();
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    public ArrayList<String> getCustomerInformation(){
        ResultSet result;
        ArrayList<String> customerData = new ArrayList<>();

        try {
            String customerStatement = "SELECT * FROM Customer WHERE CustomerID = " + customerID;
            result = statement.executeQuery(customerStatement);
            ResultSetMetaData resultMetaData = result.getMetaData();
            int columnNum = resultMetaData.getColumnCount();

            if(columnNum < 15){
                throw new RuntimeException("incomplete data set");
            }

            while (result.next()) {
                int i = 1;
                while(i <= columnNum) {
                    customerData.add(result.getString(i++));
                }
            }

            return customerData;
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }
    public Boolean UpdateCustomer(String query){
        try {
            statement.executeUpdate(query);
            return true;
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return false;
        }
    }
    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }
    public String getCustomerID(){
        return customerID;
    }
}
