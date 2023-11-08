package MainGUI;

import GUIDefaults.*;
import Logic.SQLConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerInformationPanel extends JPanel{
    private BackButton backButton;
    private DefaultButton editButton;
    private PrimaryPanel primary;
    private SQLConnection sqlConnection;
    private Border blackLine;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private StandardInputLabel customerIDInputLabel;
    private StandardInputLabel customerNameInputLabel;
    private StandardInputLabel emailInputLabel;
    private StandardInputLabel phoneInputLabel;
    private StandardInputLabel addressInputLabel;
    private StandardInputLabel tariffInputLabel;
    private StandardInputLabel meterInputLabel;

    CustomerInformationPanel(PrimaryPanel primary, SQLConnection sqlConnection){
        this.primary = primary;
        this.sqlConnection = sqlConnection;
        GridBagConstraints gbc = new GridBagConstraints();


        setBackground(Colors.backgroundColor);
        setLayout(new GridBagLayout());

        backButton = new BackButton();
        backButton.setImage();
        editButton = new DefaultButton("Edit Customer Information");

        backButton.addActionListener(new BackButtonListener());
        editButton.addActionListener(new EditCustomerListener());

        panel1 = new JPanel();
        buildPanel2();
        buildPanel3();
        panel4 = new JPanel(new BorderLayout());

        panel1.setBackground(Colors.backgroundColor);
        panel4.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        add(panel1, gbc);

        gbc.gridx = 1;
        gbc.gridy = -1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0.7;
        add(panel2, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        add(panel3, gbc);

        gbc.insets = new Insets(25,0,0,0);
        gbc.anchor = GridBagConstraints.NORTH;
        add(editButton, gbc);

        gbc.insets = new Insets(0,0,20,20);
        gbc.gridx = 2;
        gbc.gridy = -3;
        gbc.weightx = 0.15;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        add(panel4, gbc);

    }
    private void buildPanel2() {
        JLabel titleLabel = new JLabel("Customer Information");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Colors.textColor); //text color

        panel2 = new JPanel();

        panel2.setBackground(Colors.backgroundColor);
        panel2.setBorder(new EmptyBorder(45,0,20,0));

        panel2.add(titleLabel);
    }
    private void buildPanel3() {
        blackLine = BorderFactory.createLineBorder(Colors.textColor);

        StandardLabel customerIDLabel = new StandardLabel("Customer ID: ");
        StandardLabel customerNameLabel = new StandardLabel("Name: ");
        StandardLabel emailLabel = new StandardLabel("Email: ");
        StandardLabel phoneLabel = new StandardLabel("Phone: ");
        StandardLabel addressLabel = new StandardLabel("Address: ");
        StandardLabel tariffLabel = new StandardLabel("Energy Tariff: ");
        StandardLabel meterLabel = new StandardLabel("Meter Type: ");

        customerIDInputLabel = new StandardInputLabel("");
        customerNameInputLabel = new StandardInputLabel("");
        emailInputLabel = new StandardInputLabel("");
        phoneInputLabel = new StandardInputLabel("");
        addressInputLabel = new StandardInputLabel("");
        tariffInputLabel = new StandardInputLabel("");
        meterInputLabel = new StandardInputLabel("");

        GridBagConstraints gbc = new GridBagConstraints();

        panel3 = new JPanel();
        panel3.setBorder(blackLine);
        panel3.setLayout(new GridBagLayout());
        panel3.setBackground(Colors.defaultColor);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(18,18,10,10); //external padding
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(customerIDLabel, gbc);

        gbc.insets = new Insets(10,18,10,10);
        gbc.gridy = -1;
        panel3.add(customerNameLabel, gbc);

        gbc.gridy = -2;
        panel3.add(emailLabel, gbc);

        gbc.insets = new Insets(10,18,18,10);
        gbc.gridy = -3;
        panel3.add(phoneLabel, gbc);

        gbc.insets = new Insets(18,10,10,10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel3.add(customerIDInputLabel, gbc);

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy = -1;
        panel3.add(customerNameInputLabel, gbc);

        gbc.gridy = -2;
        panel3.add(emailInputLabel, gbc);

        gbc.insets = new Insets(10,10,18,10);
        gbc.gridy = -3;
        panel3.add(phoneInputLabel, gbc);

        gbc.insets = new Insets(18,10,10,10);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel3.add(addressLabel, gbc);

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy = -1;
        panel3.add(tariffLabel, gbc);

        gbc.insets = new Insets(10,10,18,10);
        gbc.gridy = -2;
        panel3.add(meterLabel, gbc);

        gbc.insets = new Insets(18,10,10,18);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel3.add(addressInputLabel, gbc);

        gbc.insets = new Insets(10,10,10,18);
        gbc.gridy = -1;
        panel3.add(tariffInputLabel, gbc);

        gbc.insets = new Insets(10,10,18,18);
        gbc.gridy = -2;
        panel3.add(meterInputLabel, gbc);
    }
    public void setCustomerInputs(){
        ArrayList<String> customerData = sqlConnection.getCustomerInformation();

        customerIDInputLabel.setText(customerData.get(0));
        customerNameInputLabel.setText(customerData.get(1) + " " + customerData.get(2));
        emailInputLabel.setText(customerData.get(3));
        phoneInputLabel.setText(customerData.get(4));
        addressInputLabel.setText("<html>" + customerData.get(5) + "<br/> " + customerData.get(6) + ", " + customerData.get(7) + " " + customerData.get(8) + "</html>");
        tariffInputLabel.setText(customerData.get(9) + "¢ / kWh");
        meterInputLabel.setText(customerData.get(10));

    }
    private class EditCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.EditCustomer();
        }
    }
    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.ReturnCustomerSelectionPanel();
        }
    }
}