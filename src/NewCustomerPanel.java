import GUIDefaults.*;
import Logic.SQLConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

//this panel contains all the input fields for a new customer
public class NewCustomerPanel extends JPanel {
    private JOptionPane NewCustomerPane;
    private JOptionPane invalidCustomerPane;
    private DefaultButton confirmButton;
    private CautionButton cancelButton;
    private PrimaryPanel primary;
    private SQLConnection sqlConnection;
    private BasicAlertPanel alertPanel1;
    private BasicAlertPanel alertPanel2;
    private Border blackLine;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private ConfirmCancelPanel deleteConfirmation;
    private JOptionPane confirmationPane;
    private StandardInputTextField customerFirstnameInputTextField;
    private StandardInputTextField customerLastnameInputTextField;
    private StandardInputTextField emailInputTextField;
    private StandardInputTextField phoneInputTextField;
    private StandardInputTextField addressInputTextField;
    private StandardInputTextField cityInputTextField;
    private StandardInputTextField stateInputTextField;
    private StandardInputTextField zipcodeInputTextField;
    private StandardInputTextField tariffInputTextField;
    private JComboBox meterBox;
    private String[] meterList = {"Dial", "Digital"};
    DecimalFormat formatter = new DecimalFormat(".00");

    NewCustomerPanel(PrimaryPanel primary, SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
        this.primary = primary;
        GridBagConstraints gbc = new GridBagConstraints();


        setBackground(Colors.backgroundColor);
        setLayout(new GridBagLayout());

        confirmButton = new DefaultButton("Confirm");
        cancelButton = new CautionButton("Cancel");

        cancelButton.addActionListener(new CancelButtonListener());
        confirmButton.addActionListener(new ConfirmButtonListener());

        panel1 = new JPanel();
        buildPanel2();
        buildPanel3();
        panel1.add(cancelButton);
        panel1.add(confirmButton);

        panel1.setBackground(Colors.backgroundColor);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panel2, gbc);
        gbc.gridy = -1;
        add(panel3, gbc);
        gbc.gridy = -2;
        gbc.insets = new Insets(15, 0, 0, 0); //external padding
        add(panel1, gbc);
    }

    private void buildPanel2() {
        JLabel titleLabel = new JLabel("Customer Information");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Colors.textColor); //text color

        panel2 = new JPanel();

        panel2.setBackground(Colors.backgroundColor);
        panel2.setBorder(new EmptyBorder(45, 0, 10, 0));

        panel2.add(titleLabel);
    }

    private void buildPanel3() {
        blackLine = BorderFactory.createLineBorder(Colors.textColor);

        StandardLabel firstnameLabel = new StandardLabel("Firstname: ");
        StandardLabel lastnameLabel = new StandardLabel("Lastname: ");
        StandardLabel emailLabel = new StandardLabel("Email: ");
        StandardLabel phoneLabel = new StandardLabel("Phone: ");
        StandardLabel addressLabel = new StandardLabel("Address: ");
        StandardLabel cityLabel = new StandardLabel("City: ");
        StandardLabel stateLabel = new StandardLabel("State: ");
        StandardLabel zipCodeLabel = new StandardLabel("Zip Code: ");
        StandardLabel tariffLabel = new StandardLabel("Energy Tariff: ");
        StandardLabel meterLabel = new StandardLabel("Meter Type: ");
        StandardLabel tariffUnitLabel = new StandardLabel("¢ / kWh");

        customerFirstnameInputTextField = new StandardInputTextField(10);
        customerLastnameInputTextField = new StandardInputTextField(10);
        emailInputTextField = new StandardInputTextField(10);
        phoneInputTextField = new StandardInputTextField(10);
        addressInputTextField = new StandardInputTextField(10);
        cityInputTextField = new StandardInputTextField(10);
        stateInputTextField = new StandardInputTextField(10);
        zipcodeInputTextField = new StandardInputTextField(10);
        tariffInputTextField = new StandardInputTextField(4);

        meterBox = new JComboBox(meterList);
        meterBox.setSelectedIndex(0);

        GridBagConstraints gbc = new GridBagConstraints();

        panel3 = new JPanel();
        panel3.setBorder(blackLine);
        panel3.setLayout(new GridBagLayout());
        panel3.setBackground(Colors.defaultColor);

        panel4 = new JPanel();
        panel4.setBackground(Colors.defaultColor);
        panel4.add(tariffInputTextField);
        panel4.add(tariffUnitLabel);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(18, 18, 10, 10); //external padding
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(firstnameLabel, gbc);

        gbc.insets = new Insets(10, 18, 10, 10);
        gbc.gridy = -1;
        panel3.add(lastnameLabel, gbc);

        gbc.gridy = -2;
        panel3.add(emailLabel, gbc);

        gbc.gridy = -3;
        panel3.add(phoneLabel, gbc);

        gbc.insets = new Insets(10, 18, 18, 10);
        gbc.gridy = -4;
        panel3.add(meterLabel, gbc);

        gbc.insets = new Insets(18, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel3.add(customerFirstnameInputTextField, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = -1;
        panel3.add(customerLastnameInputTextField, gbc);

        gbc.gridy = -2;
        panel3.add(emailInputTextField, gbc);

        gbc.gridy = -3;
        panel3.add(phoneInputTextField, gbc);

        gbc.gridy = -4;
        panel3.add(meterBox, gbc);

        gbc.insets = new Insets(18, 10, 10, 10);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel3.add(addressLabel, gbc);

        gbc.insets = new Insets(18, 10, 10, 10);
        gbc.gridy = -1;
        panel3.add(cityLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = -2;
        panel3.add(stateLabel, gbc);

        gbc.gridy = -3;
        panel3.add(zipCodeLabel, gbc);

        gbc.insets = new Insets(10, 10, 18, 10);
        gbc.gridy = -4;
        panel3.add(tariffLabel, gbc);

        gbc.insets = new Insets(18, 10, 10, 18);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel3.add(addressInputTextField, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = -1;
        panel3.add(cityInputTextField, gbc);

        gbc.gridy = -2;
        panel3.add(stateInputTextField, gbc);

        gbc.gridy = -3;
        panel3.add(zipcodeInputTextField, gbc);


        gbc.insets = new Insets(10, 8, 18, 18);
        gbc.gridy = -4;
        panel3.add(panel4, gbc);

        alertPanel1 = new BasicAlertPanel(primary);
        alertPanel1.setContinueActionListener2();
        alertPanel2 = new BasicAlertPanel(primary);
        alertPanel2.setText("Error: Creation failed", "OK", 84);
        alertPanel2.setContinueActionListener2();

        deleteConfirmation = new ConfirmCancelPanel(primary);
        deleteConfirmation.setText("<html><b><center>Are you sure you like to<br/cancel?</html></b></center>", "Any unsaved progress will be lost");
        deleteConfirmation.setConfirmCreationActionListener();
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            confirmationPane = new JOptionPane();
            confirmationPane.showOptionDialog(null, deleteConfirmation,
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        }
    }

    private class ConfirmButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("test1");
//            try {
//                statement = primary.getConnectionStatement();
            boolean flag = false;
            try {
                int randomID = ThreadLocalRandom.current().nextInt(10000000, 100000000);
                int randomEnergyUsage = ThreadLocalRandom.current().nextInt(600, 1200);
                double totalCost = randomEnergyUsage * (Double.valueOf(tariffInputTextField.getText()) / 100);
                String newUserId = randomID + "";
                String energyUsage = randomEnergyUsage + "";

                String query = "INSERT INTO Customer " +
                        "(CustomerID, CustomerFirstName, CustomerLastName, Email, PhoneNumber, Address, City, State, Zip, EnergyTariff, MeterType, EnergyUsed, TotalDue, RemainingBalance, Paid) "
                        + "VALUES ('" + newUserId
                        + "', '" + customerFirstnameInputTextField.getText()
                        + "', '" + customerLastnameInputTextField.getText()
                        + "', '" + emailInputTextField.getText()
                        + "', '" + phoneInputTextField.getText()
                        + "', '" + addressInputTextField.getText()
                        + "', '" + cityInputTextField.getText()
                        + "', '" + stateInputTextField.getText()
                        + "', '" + zipcodeInputTextField.getText()
                        + "', " + tariffInputTextField.getText()
                        + ", '" + meterBox.getSelectedItem()
                        + "', " + energyUsage
                        + ", " + formatter.format(totalCost)
                        + ", " + formatter.format(totalCost)
                        + ", " + 0
                        + ")";
                System.out.println(query);
                System.out.println("test2");
                flag = sqlConnection.UpdateCustomer(query);
                alertPanel1.setText("Customer " + newUserId + " created", "Continue", 94);
            }
            catch (Exception ex) {
                System.out.println("value error");
            }
            if(flag) {
                NewCustomerPane = new JOptionPane();
                NewCustomerPane.showOptionDialog(null, alertPanel1,
                        "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
            else {
                invalidCustomerPane = new JOptionPane();

                invalidCustomerPane.showOptionDialog(null, alertPanel2, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        }
    }

    //this is for when the user leaves the screen
    public void setText() {
        customerFirstnameInputTextField.setText("");
        customerLastnameInputTextField.setText("");
        emailInputTextField.setText("");
        phoneInputTextField.setText("");
        meterBox.setSelectedIndex(0);
        addressInputTextField.setText("");
        cityInputTextField.setText("");
        stateInputTextField.setText("");
        zipcodeInputTextField.setText("");
        tariffInputTextField.setText("");
    }
}