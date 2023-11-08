import javax.swing.*;
import java.awt.*;
import java.sql.Statement;

public class GUI extends JFrame{
    final int WINDOW_WIDTH = 900;
    final int WINDOW_HEIGHT = 500;
/*    private CardLayout cl;
    private String customerID;
    private String userID;*/
    public GUI() {
        //sets default width and height
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //sets title
        setTitle("Kansas City Energy Cooperative");
        //sets close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centers panel in window
        setLocationRelativeTo(null);

        //sets the icon for the application
        ImageIcon img = new ImageIcon("src/Application_Icon.png");
        setIconImage(img.getImage());

        buildFrame();

        setVisible(true);
    }
    private void buildFrame(){
        PrimaryPanel primary = new PrimaryPanel();

        //an instance of the primary panel is passed to the menu bar constructor
        //used to call the primary panel sign-put warning
        MenuBar menuBar = new MenuBar(primary);

        //an instance of menu bar is set with a primary panel setter
        //this allows primary panel to change the visibility and text of the menu bar
        primary.setMenuBarInstance(menuBar);

        setLayout(new GridLayout());

        //both the menu bar and primary panel are added to the base panel
        setJMenuBar(menuBar);
        add(primary);
    }
/*    private void PrimaryPanel() {
        cl = new CardLayout(0,0);
        setLayout(cl);

        //each panel gets an instance of the primary panel to access functions in the primary panel
        LoginPanel login = new LoginPanel(this);
        SelectionPanel selection = new SelectionPanel(this);
        NewCustomerPanel newCustomer = new NewCustomerPanel(this);
        CustomerSelectionPanel customerSelection = new CustomerSelectionPanel(this);
        CustomerInformationPanel customerInformation = new CustomerInformationPanel(this);
        EditCustomerPanel editCustomerPanel = new EditCustomerPanel(this);
        ProcessPaymentPanel processPayment = new ProcessPaymentPanel(this);
        EnergyUsagePanel energyUsage = new EnergyUsagePanel(this);
        InvoicePanel invoice = new InvoicePanel(this);

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
    *//*
        public Statement getConnectionStatement(){
            return statement;
        }
    *//*
    public String getCustomerID(){
        return customerID;
    }*/
    public static void main(String[] args) {
        new GUI();
    }
}
