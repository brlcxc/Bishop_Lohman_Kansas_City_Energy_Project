import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this panel is used for warning the user of an invalid customer ID entry in the selection panel
public class InvalidCustomerIDPanel extends JPanel {
    private JOptionPane InvalidCustomerIDPane;
    private DefaultButton continueButton;
    private SelectionPanel selection;
    private JLabel label;
    InvalidCustomerIDPanel(SelectionPanel selection){
        this.selection = selection;
        GridBagConstraints gbc = new GridBagConstraints();

        setPreferredSize(new Dimension(242, 100));
        setLayout(new GridBagLayout());

        continueButton = new DefaultButton("OK");
        continueButton.setPreferredSize(new Dimension(74, 24));
        continueButton.addActionListener(new ContinueButtonListener());

        buildLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label, gbc);

        gbc.insets = new Insets(15,0,0,0);
        gbc.gridy = -1;
        add(continueButton, gbc);
    }
    private void buildLabel(){
        Image baseImage = new ImageIcon("src/Application_Icon.png").getImage();
        baseImage = baseImage.getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon labelImage = new ImageIcon(baseImage);

        label = new JLabel("Invalid user ID entered");

        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(Colors.textColor);
        label.setIcon(labelImage);
    }
    private class ContinueButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            selection.Previous();
            InvalidCustomerIDPane.getRootFrame().dispose();
        }
    }
}
