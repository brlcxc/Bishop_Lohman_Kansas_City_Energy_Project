import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this panel is made for warning messages to overlay onto JOptionPane
public class BasicAlertPanel extends JPanel {
    private JOptionPane basePane;
    private PrimaryPanel primary;
    private DefaultButton button;
    private JLabel label;
    BasicAlertPanel(PrimaryPanel primary){
        this.primary = primary;
        GridBagConstraints gbc = new GridBagConstraints();

        //dimension of pop up box
        setPreferredSize(new Dimension(242, 100));
        setLayout(new GridBagLayout());

        //text set to blank since each panel text is unique
        button = new DefaultButton("");

        buildLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label, gbc);

        gbc.insets = new Insets(15,0,0,0);
        gbc.gridy = -1;
        add(button, gbc);
    }
    private void buildLabel(){
        Image baseImage = new ImageIcon("src/Application_Icon.png").getImage();
        baseImage = baseImage.getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon labelImage = new ImageIcon(baseImage);

        label = new JLabel("");

        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(Colors.textColor);
        label.setIcon(labelImage);
    }
    public void setText(String labelText, String buttonText, int buttonWidth){
        label.setText(labelText);
        button.setText(buttonText);
        button.setPreferredSize(new Dimension(buttonWidth, 24));
    }
    //there is a unique continue operation depending on what situation it is used in
    //the proper operation is set for each situation
    public void setContinueActionListener1(){
        button.addActionListener(new ContinueButtonListener1());
    }
    public void setContinueActionListener2(){
        button.addActionListener(new ContinueButtonListener2());
    }

    //used for going to customer selection panel
    private class ContinueButtonListener1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.ReturnCustomerSelectionPanel();
            basePane.getRootFrame().dispose();
        }
    }
    //used for going to selection panel
    private class ContinueButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            primary.SelectionPanel();
            basePane.getRootFrame().dispose();
        }
    }
}