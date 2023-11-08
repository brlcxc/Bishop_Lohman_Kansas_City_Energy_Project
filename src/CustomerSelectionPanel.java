import GUIDefaults.BackButton;
import GUIDefaults.Colors;
import GUIDefaults.CustomerSelectionButton;
import GUIDefaults.DeleteButton;
import Logic.SQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this panel contains all options the user can perform regarding the customer
public class CustomerSelectionPanel extends JPanel {
    private PrimaryPanel primary;
    private SQLConnection sqlConnection;
    private DeleteCustomerPanel deleteConfirmation;
    private JOptionPane confirmationPane;
    private BackButton backButton;
    private CustomerSelectionButton customerInformationButton;
    private CustomerSelectionButton processPaymentButton;
    private CustomerSelectionButton energyUsageButton;
    private CustomerSelectionButton invoiceButton;
    private DeleteButton deleteButton;
    CustomerSelectionPanel(PrimaryPanel primary, SQLConnection sqlConnection){
        this.primary = primary;
        this.sqlConnection = sqlConnection;
        GridBagConstraints gbc = new GridBagConstraints();

        setBackground(Colors.backgroundColor);
        setLayout(new GridBagLayout());

        customerInformationButton = new CustomerSelectionButton("<html><b><center>View Customer<br/Information</center></b></html>");
        processPaymentButton = new CustomerSelectionButton("<html><b><center>Process<br/Payment</center></b></html>");
        deleteButton = new DeleteButton("<html><b><center>Delete<br/Customer</center></b></html>");
        energyUsageButton = new CustomerSelectionButton("<html><b><center>View Energy<br/Usage</center></b></html>");
        invoiceButton = new CustomerSelectionButton("<html><b><center>Send<br/Invoice</center></b></html>");

        backButton = new BackButton();
        backButton.setImage();

        customerInformationButton.addActionListener(new InformationButtonListener());
        processPaymentButton.addActionListener(new PaymentButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        backButton.addActionListener(new BackButtonListener());
        energyUsageButton.addActionListener(new EnergyButtonListener());
        invoiceButton.addActionListener(new InvoiceButtonListener());

        gbc.insets = new Insets(40,50,40,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(customerInformationButton, gbc);

        gbc.gridx = 1;
        add(processPaymentButton, gbc);

        gbc.gridx = 2;
        add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = -1;
        add(energyUsageButton, gbc);

        gbc.gridx = 1;
        add(invoiceButton, gbc);

        gbc.gridx = 2;
        add(backButton, gbc);

        deleteConfirmation = new DeleteCustomerPanel(primary, sqlConnection);
        deleteConfirmation.setText("<html><b><center>Are you sure you want to delete this<br/customer profile?</html></b></center>", "This action can not be undone");
        deleteConfirmation.setConfirmDeleteActionListener();
    }
    private class InformationButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.CustomerInformation();
        }
    }
    private class PaymentButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.ProcessPayment();
        }
    }
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            confirmationPane = new JOptionPane();
            confirmationPane.showOptionDialog(null, deleteConfirmation,
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        }
    }
    private class EnergyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.EnergyUsage();
        }
    }
    private class InvoiceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.Invoice();
        }
    }
    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.SelectionPanel();
        }
    }
}