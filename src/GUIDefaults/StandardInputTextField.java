package GUIDefaults;

import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

//standard text field for user input
public class StandardInputTextField extends JTextField {
    private Border blackLine;
    public StandardInputTextField(int columns) {
        blackLine = BorderFactory.createLineBorder(Colors.textColor);

        super.setColumns(columns);
        setMinimumSize(new Dimension(40, 20));
        setBorder(blackLine);

    }
}
