import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//this is the default button where the color change is standardized between the buttons
public class DefaultButton extends JButton {
    DefaultButton(String buttonText){
        setBackground(Colors.buttonColor);
        setForeground(Colors.buttonTextColor);
        setText(buttonText);

        addMouseListener(new MouseListener());
    }
    private class MouseListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            setBackground(Colors.buttonHoverColor);
        }
        public void mouseExited(MouseEvent e) {
            setBackground(Colors.buttonColor);
        }
    }
}