import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

//this panel is used for entering customer payments
public class ProcessPaymentPanel extends JPanel {
    private BackButton backButton;
    private PrimaryPanel primary;
    private Statement statement;
    private DefaultButton makePaymentButton;
    private DefaultButton payFullButton;
    private BasicAlertPanel alertPanel1;
    private BasicAlertPanel alertPanel2;
    private BasicAlertPanel alertPanel3;
    private BasicAlertPanel alertPanel4;
    private JOptionPane pane1;
    private JOptionPane pane2;
    private JOptionPane pane3;
    private JOptionPane pane4;
    private String customerID;
    private Border blackLine;
    private StandardInputTextField paymentInputField;
    private StandardInputLabel paymentIndicator;
    private StandardInputLabel userTotalCost;
    private StandardInputLabel userAmountDue;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel7;
    DecimalFormat formatter = new DecimalFormat(".00");

    ProcessPaymentPanel(PrimaryPanel primary) {
        blackLine = BorderFactory.createLineBorder(Colors.textColor);
        this.primary = primary;
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

        alertPanel1 = new BasicAlertPanel(primary);
        alertPanel1.setText("Payment received!", "Continue", 94);
        alertPanel1.setContinueActionListener1();

        alertPanel2 = new BasicAlertPanel(primary);
        alertPanel2.setText("Already payed", "Continue", 94);
        alertPanel2.setContinueActionListener1();

        alertPanel3 = new BasicAlertPanel(primary);
        alertPanel3.setText("Payment failed", "Continue", 94);
        alertPanel3.setContinueActionListener1();

        alertPanel4 = new BasicAlertPanel(primary);
        alertPanel4.setText("Payed in full!", "Continue", 94);
        alertPanel4.setContinueActionListener1();

    }

    private void buildPanel2() {
        JLabel titleLabel = new JLabel("Process Customer Payment");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Colors.textColor); //text color

        panel2 = new JPanel();

        panel2.setBackground(Colors.backgroundColor);
        panel2.setBorder(new EmptyBorder(45, 0, 20, 0));

        panel2.add(titleLabel);
    }

    private void buildPanel3() {
        //at the beginning of the program energy cost and amount due should be the same but decrease once payments are made
        StandardLabel totalCost = new StandardLabel(" Total energy cost:");
        StandardLabel amountDue = new StandardLabel(" Amount due:");
        StandardLabel paymentStatus = new StandardLabel(" Payment status:");
        paymentIndicator = new StandardInputLabel(" Paid");
        userTotalCost = new StandardInputLabel("$0.0");
        userAmountDue = new StandardInputLabel("$0.0");

        GridBagConstraints gbc = new GridBagConstraints();

        buildPanel7();

        panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setBorder(blackLine);
        panel3.setBackground(Colors.defaultColor);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(18, 18, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(totalCost, gbc);

        gbc.insets = new Insets(10, 18, 10, 10);
        gbc.gridy = -1;
        panel3.add(amountDue, gbc);

        gbc.insets = new Insets(10, 18, 18, 10);
        gbc.gridy = -2;
        panel3.add(paymentStatus, gbc);

        gbc.insets = new Insets(18, 10, 10, 18);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel3.add(userTotalCost, gbc);

        gbc.insets = new Insets(10, 10, 10, 18);
        gbc.gridy = -1;
        panel3.add(userAmountDue, gbc);

        gbc.insets = new Insets(10, 10, 18, 18);
        gbc.gridy = -2;
        panel3.add(paymentIndicator, gbc);

        gbc.insets = new Insets(0, 18, 18, 18);
        gbc.gridx = 0;
        gbc.gridy = -3;
        gbc.gridwidth = 2;
        panel3.add(panel7, gbc);
    }

    private void buildPanel7() {
        StandardLabel inputPrompt = new StandardLabel("Enter payment: $");
        paymentInputField = new StandardInputTextField(6);
        makePaymentButton = new DefaultButton("Make payment");
        payFullButton = new DefaultButton("Pay in full");

        makePaymentButton.addActionListener(new PaymentButtonListener());
        payFullButton.addActionListener(new PayFullButtonListener());

        panel7 = new JPanel();
        panel7.setBackground(Colors.defaultColor);
        panel7.add(inputPrompt);
        panel7.add(paymentInputField);
        panel7.add(makePaymentButton);
        panel7.add(payFullButton);
    }

    public void setPaymentText() {
        statement = primary.getConnectionStatement();
        customerID = primary.getCustomerID();

        try {
            String customerStatement = "SELECT * FROM Customer WHERE CustomerID = " + customerID;
            ResultSet result;
            result = statement.executeQuery(customerStatement);
            result.next();

            userTotalCost.setText("$" + result.getString(13));
            userAmountDue.setText("$" + result.getString(14));
            if (result.getString(15).equals("0")) {
                paymentIndicator.setText("Unpaid");
            } else {
                paymentIndicator.setText("Paid");
            }

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.ReturnCustomerSelectionPanel();
        }
    }

    private class PayFullButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            pane1 = new JOptionPane();
            pane4 = new JOptionPane();

            paymentInputField.setText("");
            try {
                String customerStatement = "SELECT * FROM Customer WHERE CustomerID = " + customerID;
                ResultSet result;
                result = statement.executeQuery(customerStatement);
                result.next();

                if (result.getString(15).equals("1")) {
                    pane2.showOptionDialog(null, alertPanel2, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                } else {
                    String query = "UPDATE Customer SET " +
                            "RemainingBalance = 0" +
                            ", Paid = 1" +
                            " WHERE CustomerID = " + customerID;
                    System.out.println(query);
                    statement.executeUpdate(query);
                    pane4.showOptionDialog(null, alertPanel4, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                }
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
                pane3.showOptionDialog(null, alertPanel3, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        }
    }

    private class PaymentButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            pane1 = new JOptionPane();
            pane2 = new JOptionPane();
            pane3 = new JOptionPane();
            try {
                String customerStatement = "SELECT * FROM Customer WHERE CustomerID = " + customerID;
                ResultSet result;
                result = statement.executeQuery(customerStatement);
                result.next();

                if (result.getString(15).equals("1")) {
                    pane2.showOptionDialog(null, alertPanel2, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                } else {
                    double currentAmount = Double.parseDouble((result.getString(14)));
                    double paymentInput = Double.parseDouble(paymentInputField.getText());
                    double newAmount;
                    String paymentStatus = "0";

                    if (currentAmount >= paymentInput) {
                        newAmount = currentAmount - paymentInput;
                    }
                    else {
                        throw new Exception("Too high");
                    }
                    if (newAmount == 0) {
                        paymentStatus = "1";
                    }
                    String query = "UPDATE Customer SET " +
                            "RemainingBalance = " + formatter.format(newAmount) +
                            ", Paid = " + paymentStatus +
                            " WHERE CustomerID = " + customerID;
                    System.out.println(query);
                    statement.executeUpdate(query);
                    pane1.showOptionDialog(null, alertPanel1, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                }

            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
                pane3.showOptionDialog(null, alertPanel3, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
            paymentInputField.setText("");
        }
    }
}