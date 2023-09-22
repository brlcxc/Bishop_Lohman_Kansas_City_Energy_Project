import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BackButton extends JButton {
    BackButton() {
        setBackground(Colors.backButtonColor);
        setPreferredSize(new Dimension(85,85));
        addMouseListener(new MouseListener());

        //Note: There is no default action listener because the back operation is unique in each situation
    }
    public void setImage(){
        //image is first taken as type "Image" to be resized before being converted to type "ImageIcon"
        Image baseImage = new ImageIcon("src/returnIcon.png").getImage();
        baseImage = baseImage.getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon labelImage = new ImageIcon(baseImage);
        setIcon(labelImage);
    }

    //sets color for entering and leaving button object
    private class MouseListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            setBackground(Colors.backButtonHoverColor);
        }
        public void mouseExited(MouseEvent e) {
            setBackground(Colors.backButtonColor);
        }
    }
}
