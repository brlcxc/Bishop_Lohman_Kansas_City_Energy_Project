package MainGUI;

import GUIDefaults.BackButton;
import GUIDefaults.Colors;
import GUIDefaults.StandardInputLabel;
import GUIDefaults.StandardLabel;
import Logic.SQLConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//this panel just displays certain information regarding customer energy usage
public class EnergyUsagePanel extends JPanel {
    private PrimaryPanel primary;
    private SQLConnection sqlConnection;
    private BackButton backButton;
    private Border blackLine;
    private StandardInputLabel energyUsageInput;
    private StandardInputLabel tariffInput;
    private StandardInputLabel meterTypeInput;
    private StandardInputLabel userAmountDueInput;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    EnergyUsagePanel(PrimaryPanel primary, SQLConnection sqlConnection) {
        blackLine = BorderFactory.createLineBorder(Colors.textColor);
        this.primary = primary;
        this.sqlConnection = sqlConnection;
        GridBagConstraints gbc = new GridBagConstraints();

        setBackground(Colors.backgroundColor);
        setLayout(new GridBagLayout());

        backButton = new BackButton();
        backButton.setImage();

        backButton.addActionListener(new BackButtonListener());

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
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0.7;
        add(panel2, gbc);
        gbc.gridy = -1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panel3, gbc);

        gbc.insets = new Insets(0, 0, 20, 20);
        gbc.gridx = 2;
        gbc.gridy = -3;
        gbc.weightx = 0.15;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        add(panel4, gbc);
    }

    private void buildPanel2() {
        JLabel titleLabel = new JLabel("Energy Usage Breakdown");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Colors.textColor); //text color

        panel2 = new JPanel();

        panel2.setBackground(Colors.backgroundColor);
        panel2.setBorder(new EmptyBorder(45, 0, 20, 0));

        panel2.add(titleLabel);
    }

    private void buildPanel3() {
        StandardLabel energyUsage = new StandardLabel("Monthly energy usage:");
        StandardLabel tariff = new StandardLabel("Energy tariff:");
        StandardLabel meterType = new StandardLabel("Meter type:");
        StandardLabel userAmountDue = new StandardLabel("Total energy cost:");

        energyUsage.setMinimumSize(new Dimension(80,24));

        energyUsageInput = new StandardInputLabel("kWh");
        tariffInput = new StandardInputLabel("¢ / kWh");
        meterTypeInput = new StandardInputLabel("None");
        userAmountDueInput = new StandardInputLabel("$0.0");

        GridBagConstraints gbc = new GridBagConstraints();

        panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setBorder(blackLine);
        panel3.setBackground(Colors.defaultColor);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(18,18,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(energyUsage, gbc);

        gbc.insets = new Insets(10,18,10,10);
        gbc.gridy = -1;
        panel3.add(tariff, gbc);

        gbc.insets = new Insets(10,18,10,10);
        gbc.gridy = -2;
        panel3.add(meterType, gbc);

        gbc.insets = new Insets(10,18,18,10);
        gbc.gridy = -3;
        panel3.add(userAmountDue, gbc);

        gbc.insets = new Insets(18,10,10,18);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel3.add(energyUsageInput, gbc);

        gbc.insets = new Insets(10,10,10,18);
        gbc.gridy = -1;
        panel3.add(tariffInput, gbc);

        gbc.gridy = -2;
        panel3.add(meterTypeInput, gbc);

        gbc.insets = new Insets(10,10,18,18);
        gbc.gridy = -3;
        panel3.add(userAmountDueInput, gbc);
    }

    public void setEnergyText() {
        ArrayList<String> customerData = sqlConnection.getCustomerInformation();

        energyUsageInput.setText(customerData.get(11) + "kWh");
        tariffInput.setText(customerData.get(9) + "¢ / kWh");
        meterTypeInput.setText(customerData.get(10));
        userAmountDueInput.setText("$" + customerData.get(12));
    }

    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.ReturnCustomerSelectionPanel();
        }
    }
}