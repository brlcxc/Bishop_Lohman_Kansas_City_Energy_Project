import GUIDefaults.Colors;
import GUIDefaults.DefaultButton;
import Logic.SQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this panel allows the user to select the option to create a new customer or view an existing one
public class SelectionPanel extends JPanel {
    private PrimaryPanel primary;
    private CustomerIDPanel customerID;
    private InvalidCustomerIDPanel invalidCustomerID;
    private DefaultButton newCustomerButton;
    private DefaultButton existingUserButton;
    private JOptionPane customerIDPane;
    private CardLayout cl;
    private JPanel panel1;
    public SelectionPanel(PrimaryPanel primary, SQLConnection sqlConnection){
        this.primary = primary;


        customerID = new CustomerIDPanel(primary, this, sqlConnection);
        invalidCustomerID = new InvalidCustomerIDPanel(this);
        setBackground(Colors.backgroundColor);
        newCustomerButton = new DefaultButton("<html><b><center>Create New<br/Customer Profile</center></b></html>");
        existingUserButton = new DefaultButton("<html><b><center>Access<br/Existing Customer</center></b></html>");

        existingUserButton.setPreferredSize(new Dimension(220, 130));
        newCustomerButton.setPreferredSize(new Dimension(220, 130));

        existingUserButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        newCustomerButton.setFont(new Font("SansSerif", Font.BOLD, 24));

        existingUserButton.addActionListener(new ExistingUserButtonListener());
        newCustomerButton.addActionListener(new NewUserButtonListener());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0,10,0,10); //external padding
        gbc.ipadx = 30; //internal x padding
        gbc.ipady = 30; //internal y padding

        add(newCustomerButton, gbc);
        add(existingUserButton, gbc);

        buildPanel1();
    }
    private class ExistingUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //the panel uses car layout so the JOptionPane switches to a warning upon an incorrect entry
            customerIDPane = new JOptionPane();
            customerID.setJOptionPane(customerIDPane);
            customerIDPane.showOptionDialog(null, panel1, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        }
    }
    public void buildPanel1(){
        cl = new CardLayout(0,0);

        panel1 = new JPanel();
        panel1.setLayout(cl);

        panel1.add(customerID, "userID");
        panel1.add(invalidCustomerID, "invalidUserID");
    }
    public void Next(){
        cl.next(panel1);
    }
    public void Previous(){
        cl.previous(panel1);
    }
    private class NewUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.NewCustomer();
        }
    }
}