import GUIDefaults.Colors;
import GUIDefaults.DefaultButton;
import Logic.SQLConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this panel is for the user entering the customer ID
public class CustomerIDPanel extends JPanel {
    private PrimaryPanel primary;
    private SQLConnection sqlConnection;
    private SelectionPanel selection;
    private JOptionPane customerIDPane;
    private DefaultButton continueButton;
    private DefaultButton cancelButton;
    private JTextField customerIDInput;
    private JPanel panel1;
    private JLabel label;
    CustomerIDPanel(PrimaryPanel primary, SelectionPanel selection, SQLConnection sqlConnection){
        Border blackLine = BorderFactory.createLineBorder(Colors.textColor);
        this.selection = selection;
        this.primary = primary;
        this.sqlConnection = sqlConnection;

        GridBagConstraints gbc = new GridBagConstraints();
        customerIDInput = new JTextField(12);

        customerIDInput.setBorder(blackLine);
        customerIDInput.addActionListener(new ContinueListener());

        setLayout(new GridBagLayout());

        buildLabel();
        gbc.insets = new Insets(5,5,0,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(label, gbc);

        gbc.gridy = -1;
        gbc.ipady = 6;
        add(customerIDInput, gbc);

        buildPanel1();
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = -2;
        add(panel1, gbc);
    }
    private void buildPanel1(){
        GridBagConstraints gbc = new GridBagConstraints();

        continueButton = new DefaultButton("Continue");
        cancelButton = new DefaultButton("Cancel");

        continueButton.setPreferredSize(new Dimension(88, 24));
        cancelButton.setPreferredSize(new Dimension(88, 24));


        continueButton.addActionListener(new ContinueListener());
        cancelButton.addActionListener(new CancelButtonListener());

        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,0,5);
        panel1.add(continueButton,gbc);
        gbc.gridx = 1;
        panel1.add(cancelButton,gbc);
    }
    private void buildLabel(){
        Image baseImage = new ImageIcon("src/Application_Icon.png").getImage();
        baseImage = baseImage.getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon labelImage = new ImageIcon(baseImage);

        label = new JLabel("Please enter a valid customer ID");

        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(Colors.textColor);
        label.setIcon(labelImage);
    }
    void setJOptionPane(JOptionPane customerIDPane){
        this.customerIDPane = customerIDPane;
    }
    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerIDInput.setText("");
            customerIDPane.getRootFrame().dispose();
        }
    }
    private class ContinueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = customerIDInput.getText();
//            String customerStatement = "SELECT CustomerFirstName FROM Customer WHERE CustomerID = " + userInput;
//            Statement statement = primary.getConnectionStatement();

//            try {
////                ResultSet result = sqlConnection.getCustomerInformation(userInput);
//                if (!sqlConnection.validateCustomerID(userInput)) {
//                    System.out.println("No data");
//                    throw new RuntimeException();
//                }
//                primary.CustomerSelectionPanel(userInput);
//                customerIDPane.getRootFrame().dispose();
//            }
//            catch (Exception ex) {
//                selection.Next();
//            }
            if (!sqlConnection.ValidateCustomerID(userInput)) {
                System.out.println("No data");
                selection.Next();
            }
            else{
                sqlConnection.setCustomerID(userInput);
                primary.CustomerSelectionPanel();
                customerIDPane.getRootFrame().dispose();
            }
            customerIDInput.setText("");
        }
    }
}
