import GUIDefaults.Colors;
import GUIDefaults.DefaultButton;
import Logic.SQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

//this panel acts as the login panel which is the first panel the user sees
public class LoginPanel extends JPanel{
    private PrimaryPanel primary;
    private SQLConnection sqlConnection;
    private MenuBar menuBar;
    private DefaultButton loginButton;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel errorLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Border blackLine;
    private Border redLine;
    private GridBagConstraints panel2gbc;
    private boolean loginFlag;
    public LoginPanel(PrimaryPanel primary, SQLConnection sqlConnection){
        this.sqlConnection = sqlConnection;
        this.primary = primary;

        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Colors.backgroundColor);

        setLayout(new GridBagLayout());

        //panel for welcome label
        buildPanel1();
        gbc.gridy = 0; //gridx must be set for gridy to work
        gbc.gridx = 0;
        gbc.weighty = 0.2;
        add(panel1, gbc);

        //panel for credentials and login
        buildPanel2();
        gbc.gridy = -1;
        gbc.weighty = 0.8;
        add(panel2, gbc);
    }
    private void buildPanel1() {
        //<center> centers within while SwingConstants.CENTER centers the panel as a whole
        JLabel welcomeLabel = new JLabel("<html><b><center>Welcome to Kansas City Energy Cooperative<br" +
                "/Customer Viewer</center></b></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        welcomeLabel.setForeground(Colors.textColor); //text color

        panel1 = new JPanel();

        panel1.setBackground(Colors.backgroundColor);
        panel1.setBorder(new EmptyBorder(30,0,0,0));

        panel1.add(welcomeLabel);
    }
    private void buildPanel2() {
        blackLine = BorderFactory.createLineBorder(Colors.textColor);
        redLine = BorderFactory.createLineBorder(Colors.cautionHoverColor);

        JLabel promptLabel = new JLabel("Please enter your username and password",
                SwingConstants.CENTER);
        JLabel usernameLabel = new JLabel("Username: ", SwingConstants.RIGHT);
        JLabel passwordLabel = new JLabel("Password: ", SwingConstants.RIGHT);
        errorLabel = new JLabel("The username or password is incorrect", SwingConstants.CENTER);

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        loginButton = new DefaultButton("Login");

        promptLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        //increases label pixel size
        usernameLabel.setPreferredSize(new Dimension(140, 16));
        passwordLabel.setPreferredSize(new Dimension(138, 16));
        //color of labels
        promptLabel.setForeground(Colors.textColor);
        usernameLabel.setForeground(Colors.textColor);
        passwordLabel.setForeground(Colors.textColor);
        errorLabel.setForeground(Colors.cautionHoverColor);
        usernameField.setBorder(blackLine);
        passwordField.setBorder(blackLine);

        usernameField.addActionListener(new InputListener());
        passwordField.addActionListener(new InputListener());
        loginButton.addActionListener(new InputListener());

        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBorder(blackLine);
        panel2.setBackground(Colors.defaultColor);

        panel2gbc = new GridBagConstraints();

        //login label
        panel2gbc.insets = new Insets(14,5,20,5); //external padding
        panel2gbc.ipadx = 5; //internal padding
        panel2gbc.gridx = 0;
        panel2gbc.gridy = 0;
        panel2gbc.gridwidth = 2;
        panel2.add(promptLabel, panel2gbc);

        //username row
        panel2gbc.insets = new Insets(2,5,12,5);
        panel2gbc.ipadx = 0;
        panel2gbc.gridwidth = 1;
        panel2gbc.gridx = 0;
        panel2gbc.gridy = -1;
        panel2.add(usernameLabel, panel2gbc);
        panel2gbc.gridx = 1;
        panel2gbc.ipady = 8;
        panel2.add(usernameField, panel2gbc);

        //password row
        panel2gbc.insets = new Insets(12,5,2,5);
        panel2gbc.gridx = 0;
        panel2gbc.gridy = -2;
        panel2gbc.ipady = 0;
        panel2.add(passwordLabel, panel2gbc);
        panel2gbc.gridx = 1;
        panel2gbc.ipady = 8;
        panel2.add(passwordField, panel2gbc);

        //login button
        panel2gbc.insets = new Insets(10,5,18,5);
        panel2gbc.gridx = 0;
        panel2gbc.gridy = -3;
        panel2gbc.ipady = 0;
        panel2gbc.gridwidth = 2;
        panel2.add(loginButton, panel2gbc);
    }
    private class InputListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String usernameInput, passwordInput;
            loginFlag = false;

            usernameInput = usernameField.getText();
            passwordInput = passwordField.getText();

            loginFlag = sqlConnection.EstablishConnection(usernameInput, passwordInput);

            if(loginFlag){
                menuBar.setUserText(usernameInput);
                usernameField.setText("");
                passwordField.setText("");
                passwordField.setBorder(blackLine);
                panel2.remove(errorLabel);
                panel2.remove(loginButton);

                panel2gbc.insets = new Insets(10,5,18,5);
                panel2gbc.gridy = -3;
                panel2.add(loginButton, panel2gbc);
                panel2.repaint();
                panel2.revalidate();

                primary.NextPanel();
                menuBar.setVisible(true);
            }
            else{
                passwordField.setText("");
                passwordField.setBorder(redLine);

                panel2.remove(loginButton);

                panel2gbc.insets = new Insets(0,0,0,0);
                panel2gbc.gridwidth = 2;
                panel2.add(errorLabel, panel2gbc);
                panel2gbc.insets = new Insets(6,5,6,5);
                panel2gbc.gridy = -4;
                panel2.add(loginButton, panel2gbc);
                panel2.repaint();
                panel2.revalidate(); //needed when removing items
            }
        }
    }
    public void setMenuBarInstance(MenuBar menuBar){
        this.menuBar = menuBar;
    }
}