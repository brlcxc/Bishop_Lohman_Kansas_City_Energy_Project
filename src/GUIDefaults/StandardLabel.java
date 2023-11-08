package GUIDefaults;

import javax.swing.*;
import java.awt.*;

public class StandardLabel extends JLabel {
    public StandardLabel(String labelText){
        setText(labelText);
        setFont(new Font("SansSerif",Font.BOLD, 14));
        setForeground(Colors.textColor);
    }
}