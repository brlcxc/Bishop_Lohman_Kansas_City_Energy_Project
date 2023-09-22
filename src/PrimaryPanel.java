import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//this panel uses card layout and contains all other main panels seen within the program
//this panel also contains important information regarding the connection, user, and customer
public class PrimaryPanel extends JPanel{
    private MenuBar menuBar;
    private LoginPanel login;
    private SelectionPanel selection;
    private NewCustomerPanel newCustomer;
    private CustomerSelectionPanel customerSelection;
    private CustomerInformationPanel customerInformation;
    private EditCustomerPanel editCustomerPanel;
    private ProcessPaymentPanel processPayment;
    private EnergyUsagePanel energyUsage;
    private InvoicePanel invoice;
    private CardLayout cl;
    private Statement statement;
    private String customerID;
    private String userID;
    final String DatabaseURL = "jdbc:mysql://localhost:3306/customerdatadb";

    public PrimaryPanel() {
        cl = new CardLayout(0,0);
        setLayout(cl);

        //each panel gets an instance of the primary panel to access functions in the primary panel
        login = new LoginPanel(this);
        selection = new SelectionPanel(this);
        newCustomer = new NewCustomerPanel(this);
        customerSelection = new CustomerSelectionPanel(this);
        customerInformation = new CustomerInformationPanel(this);
        editCustomerPanel = new EditCustomerPanel(this);
        processPayment = new ProcessPaymentPanel(this);
        energyUsage = new EnergyUsagePanel(this);
        invoice = new InvoicePanel(this);

        add(login, "login");
        add(selection, "selection");
        add(newCustomer, "newCustomer");
        add(customerSelection, "customerSelection");
        add(customerInformation, "customerInformation");
        add(editCustomerPanel, "editCustomerPanel");
        add(processPayment, "processPayment");
        add(energyUsage, "energyUsage");
        add(invoice, "invoice");
    }

    public boolean EstablishConnection(String usernameInput, String passwordInput){
        try {
            Connection connection = DriverManager.getConnection(DatabaseURL, usernameInput, passwordInput);
            Statement statement = connection.createStatement();
            this.statement = statement;
            System.out.println("connected");
            return true;
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return false;
        }
    }
    public void setMenuBarInstance(MenuBar menuBar){
        this.menuBar = menuBar;
        login.setMenuBarInstance(menuBar);
    }
    //there are many unique functions for switching panels because each panel has different needs for switching
    public void NextPanel(){
        cl.next(this);
    }
    public void LoginPanel(){
        cl.show(this,"login");
        menuBar.setVisible(false);
    }
    public void CustomerSelectionPanel(String customerID){
        this.customerID = customerID;
        cl.show(this,"customerSelection");
        menuBar.setCustomerIDText("Customer ID: " + customerID);
    }
    public void ReturnCustomerSelectionPanel(){
        cl.show(this,"customerSelection");
    }
    public void SelectionPanel(){
        cl.show(this,"selection");
        userID = "";
        menuBar.setCustomerIDText("");
    }
    public void CustomerInformation(){
        customerInformation.setCustomerInputs();
        cl.show(this,"customerInformation");
    }
    public void EditCustomer(){
        editCustomerPanel.setText();
        cl.show(this,"editCustomerPanel");
    }
    public void NewCustomer(){
        newCustomer.setText();
        cl.show(this,"newCustomer");
    }
    public void ProcessPayment(){
        cl.show(this,"processPayment");
        processPayment.setPaymentText();
    }
    public void EnergyUsage(){
        cl.show(this,"energyUsage");
        energyUsage.setEnergyText();
    }
    public void Invoice(){
        invoice.setInvoiceText();
        cl.show(this,"invoice");
    }
    public Statement getConnectionStatement(){
        return statement;
    }
    public String getCustomerID(){
        return customerID;
    }
}