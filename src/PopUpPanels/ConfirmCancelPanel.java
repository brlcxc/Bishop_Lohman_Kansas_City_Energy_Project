package PopUpPanels;

import GUIDefaults.CautionButton;
import GUIDefaults.Colors;
import GUIDefaults.DefaultButton;
import MainGUI.PrimaryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this panel is made for warning messages to overlay onto JOptionPane with a confirm and cancel option
//unlike basic alert panel there are two labels and two buttons
public class ConfirmCancelPanel extends JPanel {
    private JOptionPane confirmationPane;
    private CautionButton confirmButton;
    private DefaultButton cancelButton;
    private PrimaryPanel primary;
    private JLabel label1;
    private JLabel label2;
    public ConfirmCancelPanel(PrimaryPanel primary){
        this.primary = primary;
        GridBagConstraints gbc = new GridBagConstraints();

        setPreferredSize(new Dimension(242, 100));

        cancelButton = new DefaultButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(84, 24));
        cancelButton.addActionListener(new CancelButtonListener());

        confirmButton = new CautionButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(84, 24));

        buildLabels();

        //the labels are added in the top layer
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label1, gbc);
        add(label2, gbc);

        //the buttons are added in the bottom layer
        gbc.gridy = -1;
        add(cancelButton, gbc);
        add(confirmButton, gbc);
    }

    public void setText(String labelInput1, String labelInput2){
        label1.setText(labelInput1);
        label2.setText(labelInput2);
    }

    //the text for the labels is blank by default since it is unique in each situation
    private void buildLabels(){
        Image baseImage = new ImageIcon("src/Application_Icon.png").getImage();
        baseImage = baseImage.getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon labelImage = new ImageIcon(baseImage);

        label1 = new JLabel("");

        label1.setFont(new Font("SansSerif", Font.BOLD, 12));
        label1.setForeground(Colors.textColor);

        label2 = new JLabel("");
        label2.setFont(new Font("SansSerif", Font.BOLD, 12));
        label2.setForeground(Colors.textColor);
        label1.setIcon(labelImage);
    }

    //The cancel operation is the same across all situations
    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            confirmationPane.getRootFrame().dispose();
        }
    }

    //this action listener is used for deleting the customer

    //used on sign-out panel
    private class ConfirmButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            confirmationPane.getRootFrame().dispose();
            primary.LoginPanel();
        }
    }

    //used for confirming exit when editing customer
    private class ConfirmButtonListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            confirmationPane.getRootFrame().dispose();
            primary.CustomerInformation();
        }
    }

    //this is for confirming the cancellation of a new customer creation
    private class ConfirmButtonListener4 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            confirmationPane.getRootFrame().dispose();
            primary.SelectionPanel();
        }
    }
    public void setConfirmSignOutActionListener(){
        confirmButton.addActionListener(new ConfirmButtonListener2());
    }
    public void setConfirmEditActionListener(){
        confirmButton.addActionListener(new ConfirmButtonListener3());
    }
    public void setConfirmCreationActionListener(){
        confirmButton.addActionListener(new ConfirmButtonListener4());
    }
}