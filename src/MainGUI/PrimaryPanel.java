package MainGUI;

import Logic.SQLConnection;
import MainGUI.*;
import MainGUI.MenuBar;
import PopUpPanels.LoginPanel;

import javax.swing.*;
import java.awt.*;

//this panel uses card layout and contains all other main panels seen within the program
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
    private String userID;
    private SQLConnection sqlConnection;
//    final String DatabaseURL = "jdbc:mysql://localhost:3306/customerdatadb";

    public PrimaryPanel() {
        cl = new CardLayout(0,0);
        setLayout(cl);

        sqlConnection = new SQLConnection();

        //each panel gets an instance of the primary panel to access functions in the primary panel
        login = new LoginPanel(this, sqlConnection);
        selection = new SelectionPanel(this, sqlConnection);
        newCustomer = new NewCustomerPanel(this, sqlConnection);
        customerSelection = new CustomerSelectionPanel(this, sqlConnection);
        customerInformation = new CustomerInformationPanel(this, sqlConnection);
        editCustomerPanel = new EditCustomerPanel(this, sqlConnection);
        processPayment = new ProcessPaymentPanel(this, sqlConnection);
        energyUsage = new EnergyUsagePanel(this, sqlConnection);
        invoice = new InvoicePanel(this, sqlConnection);

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
    public void CustomerSelectionPanel(){
        cl.show(this,"customerSelection");
        menuBar.setCustomerIDText("Customer ID: " + sqlConnection.getCustomerID());
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
}