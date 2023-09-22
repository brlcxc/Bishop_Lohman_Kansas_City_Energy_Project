import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

public class EditCustomerPanel extends JPanel{
    private DefaultButton confirmButton;
    private CautionButton cancelButton;
    private PrimaryPanel primary;
    private BasicAlertPanel alertPanel;
    private BasicAlertPanel alertPanel2;
    private JOptionPane updateFailedPane;
    private JOptionPane updateSuccessPane;
    private Statement statement;
    private String customerID;
    private Border blackLine;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
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
    private ConfirmCancelPanel cancelConfirmation;
    private JOptionPane confirmationPane;
    DecimalFormat formatter = new DecimalFormat(".00");
    private String[] meterList = {"Dial", "Digital"};

    EditCustomerPanel(PrimaryPanel primary){
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
        gbc.insets = new Insets(15,0,0,0); //external padding
        add(panel1, gbc);

        //a different panel is used depending on the success of the update
        alertPanel = new BasicAlertPanel(primary);
        alertPanel.setText("Update failed", "Continue", 94);
        alertPanel.setContinueActionListener1();

        alertPanel2 = new BasicAlertPanel(primary);
        alertPanel2.setText("Update success!", "Continue", 94);
        alertPanel2.setContinueActionListener1();

        cancelConfirmation = new ConfirmCancelPanel(primary);
        cancelConfirmation.setText("<html><b><center>Are you sure you like to<br/cancel?</html></b></center>", "Any unsaved progress will be lost");
        cancelConfirmation.setConfirmEditActionListener();
    }
    private void buildPanel2() {
        JLabel titleLabel = new JLabel("Edit Customer Information");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Colors.textColor); //text color

        panel2 = new JPanel();

        panel2.setBackground(Colors.backgroundColor);
        panel2.setBorder(new EmptyBorder(45,0,10,0));

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
        gbc.insets = new Insets(18,18,10,10); //external padding
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(firstnameLabel, gbc);

        gbc.insets = new Insets(10,18,10,10);
        gbc.gridy = -1;
        panel3.add(lastnameLabel, gbc);

        gbc.gridy = -2;
        panel3.add(emailLabel, gbc);

        gbc.gridy = -3;
        panel3.add(phoneLabel, gbc);

        gbc.insets = new Insets(10,18,18,10);
        gbc.gridy = -4;
        panel3.add(meterLabel, gbc);

        gbc.insets = new Insets(18,10,10,10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel3.add(customerFirstnameInputTextField, gbc);

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy = -1;
        panel3.add(customerLastnameInputTextField, gbc);

        gbc.gridy = -2;
        panel3.add(emailInputTextField, gbc);

        gbc.gridy = -3;
        panel3.add(phoneInputTextField, gbc);

        gbc.gridy = -4;
        panel3.add(meterBox, gbc);

        gbc.insets = new Insets(18,10,10,10);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel3.add(addressLabel, gbc);

        gbc.insets = new Insets(18,10,10,10);
        gbc.gridy = -1;
        panel3.add(cityLabel, gbc);

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy = -2;
        panel3.add(stateLabel, gbc);

        gbc.gridy = -3;
        panel3.add(zipCodeLabel, gbc);

        gbc.insets = new Insets(10,10,18,10);
        gbc.gridy = -4;
        panel3.add(tariffLabel, gbc);

        gbc.insets = new Insets(18,10,10,18);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel3.add(addressInputTextField, gbc);

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy = -1;
        panel3.add(cityInputTextField, gbc);

        gbc.gridy = -2;
        panel3.add(stateInputTextField, gbc);

        gbc.gridy = -3;
        panel3.add(zipcodeInputTextField, gbc);


        gbc.insets = new Insets(10,8,18,18);
        gbc.gridy = -4;
        panel3.add(panel4, gbc);
    }
    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            confirmationPane = new JOptionPane();
            confirmationPane.showOptionDialog(null, cancelConfirmation,
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        }
    }
    private class ConfirmButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            statement = primary.getConnectionStatement();
            String customerStatement = "SELECT * FROM Customer WHERE CustomerID = " + customerID;

            try{
                ResultSet result;
                result = statement.executeQuery(customerStatement);
                result.next();

                double newEnergyCost = (Double.parseDouble(tariffInputTextField.getText()) / 100) * Double.parseDouble(result.getString(12));
                //Note: there is a new cost calculated and payments reset

                String query = "UPDATE Customer SET " +
                        "CustomerFirstName = '" + customerFirstnameInputTextField.getText() +
                        "', CustomerLastName = '" + customerLastnameInputTextField.getText() +
                        "', Email = '" + emailInputTextField.getText() +
                        "', PhoneNumber = '" + phoneInputTextField.getText() +
                        "', Address = '" + addressInputTextField.getText() +
                        "', City = '" + cityInputTextField.getText() +
                        "', State = '" + stateInputTextField.getText() +
                        "', Zip = '" + zipcodeInputTextField.getText() +
                        "', EnergyTariff = " + tariffInputTextField.getText() +
                        ", MeterType = '" + meterBox.getSelectedItem() +
                        "', TotalDue = " + formatter.format(newEnergyCost) +
                        ", RemainingBalance = " + formatter.format(newEnergyCost) +
                        ", Paid = 0" +
                        " WHERE CustomerID = " + customerID;
                System.out.println(query);
                statement.executeUpdate(query);

                updateSuccessPane = new JOptionPane();
                updateFailedPane.showOptionDialog(null, alertPanel2, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
            catch (Exception ex){
                updateFailedPane = new JOptionPane();
                updateFailedPane.showOptionDialog(null, alertPanel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                System.out.println(ex.getMessage());
            }
            primary.CustomerInformation();
        }
    }

    //the existing customer information is set as the existing text
    public void setText(){
        statement = primary.getConnectionStatement();
        customerID = primary.getCustomerID();

        try {
            String customerStatement = "SELECT * FROM Customer WHERE CustomerID = " + customerID;
            ResultSet result;
            result = statement.executeQuery(customerStatement);
            result.next();

            customerFirstnameInputTextField.setText(result.getString(2));
            customerLastnameInputTextField.setText(result.getString(3));
            emailInputTextField.setText(result.getString(4));
            phoneInputTextField.setText(result.getString(5));
            addressInputTextField.setText(result.getString(6));
            cityInputTextField.setText(result.getString(7));
            stateInputTextField.setText(result.getString(8));
            zipcodeInputTextField.setText(result.getString(9));
            tariffInputTextField.setText(result.getString(10));

            if(result.getString(10).equals("Dial")){
                meterBox.setSelectedIndex(0);
            }
            else {
                meterBox.setSelectedIndex(1);
            }
        }
        catch (Exception ex) {

            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}


