package GUIDefaults;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CautionButton extends JButton {
    public CautionButton(String buttonText){
        setBackground(Colors.cautionColor);
        setForeground(Colors.buttonTextColor);
        setText(buttonText);

        addMouseListener(new MouseListener());
        //Note: There is no default action listener because the back operation is unique in each situation
    }

    //sets color for entering and leaving button object
    private class MouseListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            setBackground(Colors.cautionHoverColor);
        }
        public void mouseExited(MouseEvent e) {
            setBackground(Colors.cautionColor);
        }
    }
}
