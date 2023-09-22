import javax.swing.*;
import java.awt.*;

//standard label for describing an input
public class StandardInputLabel extends JLabel {
    StandardInputLabel(String labelText){
        setText(labelText);
        setFont(new Font("SansSerif",Font.BOLD, 14));
        setForeground(Colors.inputTextColor);
    }
}