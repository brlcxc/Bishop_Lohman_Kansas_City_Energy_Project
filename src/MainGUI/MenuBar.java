package MainGUI;

import GUIDefaults.Colors;
import GUIDefaults.DefaultButton;
import PopUpPanels.ConfirmCancelPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private JLabel usernameLabel;
    private JLabel customerIDLabel;
    private ConfirmCancelPanel deleteConfirmation;
    private JOptionPane confirmationPane;
    public MenuBar(PrimaryPanel primary) {
        DefaultButton signOutButton = new DefaultButton("Sign Out");

        setBackground(Colors.menuBarColor);

        usernameLabel = new JLabel();
        customerIDLabel = new JLabel();

        usernameLabel.setBorder(new EmptyBorder(0,0,0,4)); //external padding to the right
        usernameLabel.setForeground(Colors.textColor); //label text color
        customerIDLabel.setForeground(Colors.textColor);

        signOutButton.addActionListener(new SignOutButtonListener());
        add(customerIDLabel);
        add(Box.createHorizontalGlue()); //aligns everything right
        add(usernameLabel);
        add(signOutButton);
        setVisible(false);

        deleteConfirmation = new ConfirmCancelPanel(primary);
        deleteConfirmation.setText("<html><b><center>Are you sure you like to<br/sign out?</html></b></center>", "Any unsaved progress will be lost");
        deleteConfirmation.setConfirmSignOutActionListener();
    }
    private class SignOutButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            confirmationPane = new JOptionPane();
            confirmationPane.showOptionDialog(null, deleteConfirmation,
                    "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        }
    }
    public void setUserText(String username){
        usernameLabel.setText("User: " + username);
    }
    public void setCustomerIDText(String customerID){
        customerIDLabel.setText(customerID);
    }

}