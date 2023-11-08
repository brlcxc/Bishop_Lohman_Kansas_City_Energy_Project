package MainGUI;

import GUIDefaults.BackButton;
import GUIDefaults.Colors;
import GUIDefaults.DefaultButton;
import Logic.EmailWriter;
import Logic.MailUtil;
import Logic.SQLConnection;
import PopUpPanels.BasicAlertPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//this panel is for viewing and sending the customer invoice
public class InvoicePanel extends JPanel {
    private SQLConnection sqlConnection;
    private BackButton backButton;
    private PrimaryPanel primary;
    private DefaultButton sendButton;
    private Border blackLine;
    private BasicAlertPanel alertPanel;
    private BasicAlertPanel alertPanel2;
    private JOptionPane sendPane;
    private JOptionPane sendPane2;
    private JTextArea emailInput;
    private JScrollPane scrollPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    
    InvoicePanel(PrimaryPanel primary, SQLConnection sqlConnection) {
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
        JLabel titleLabel = new JLabel("Customer Invoice");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Colors.textColor); //text color

        panel2 = new JPanel();

        panel2.setBackground(Colors.backgroundColor);
        panel2.setBorder(new EmptyBorder(45, 0, 20, 0));

        panel2.add(titleLabel);
    }

    private void buildPanel3() {
        blackLine = BorderFactory.createLineBorder(Colors.textColor);
        GridBagConstraints gbc = new GridBagConstraints();

        sendButton = new DefaultButton("Send");
        sendButton.setPreferredSize(new Dimension(74, 24));

        sendButton.addActionListener(new SendButtonListener());

        //the text area allows for the email to be edited
        emailInput = new JTextArea();
        emailInput.setLineWrap(true);
        emailInput.setWrapStyleWord(true);

        //a scroll pane including a text area is added
        scrollPane = new JScrollPane(emailInput);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(400, 150));

        panel3 = new JPanel();
        panel3.setBorder(blackLine);
        panel3.setLayout(new GridBagLayout());
        panel3.setBackground(Colors.defaultColor);


        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(scrollPane, gbc);
        gbc.insets = new Insets(0,0,10,0);
        gbc.gridy = -1;
        panel3.add(sendButton, gbc);

        alertPanel = new BasicAlertPanel(primary);
        alertPanel.setText("Invoice sent!", "Continue", 94);
        alertPanel.setContinueActionListener1();

        alertPanel2 = new BasicAlertPanel(primary);
        alertPanel2.setText("Failed to send", "Continue", 94);
        alertPanel2.setContinueActionListener1();
    }

    //the default invoice is set
    public void setInvoiceText() {
        java.util.Date date = new java.util.Date();

        ArrayList<String> customerData = sqlConnection.getCustomerInformation();

        emailInput.setText(date +
            "\n\nDear "+ customerData.get(1) + " " + customerData.get(2) + "," +
            "\n\nYour monthly bill is $" + customerData.get(12) +
            "\n\nSincerely,\n\n" +
            "Kansas City Energy Cooperative");
    }

    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.ReturnCustomerSelectionPanel();
        }
    }

    private class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sendPane = new JOptionPane();
            sendPane2 = new JOptionPane();

            try {
                EmailWriter.writeUsingIText(emailInput.getText());
                String[] recipients = {"lohmanbishop@gmail.com"};
                String subject = "Monthly Invoice";
                String message = "This is your monthly invoice.";
                if(new MailUtil().sendMail(recipients, subject, message)){
                    sendPane.showOptionDialog(null, alertPanel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                }
                else {
                    throw new Exception("Error");
                }
            }
            catch (Exception ex) {
                sendPane2.showOptionDialog(null, alertPanel2, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        }
    }
}