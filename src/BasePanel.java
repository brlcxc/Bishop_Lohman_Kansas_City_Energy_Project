import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {
    public PrimaryPanel primary;
    public MenuBar menuBar;
    public JFrame mainframe;
    public BasePanel(JFrame mainframe){
        this.mainframe = mainframe;
        primary = new PrimaryPanel();

        //an instance of the primary panel is passed to the menu bar constructor
        //used to call the primary panel sign-put warning
        menuBar = new MenuBar(primary);

        //an instance of menu bar is set with a primary panel setter
        //this allows primary panel to change the visibility and text of the menu bar
        primary.setMenuBarInstance(menuBar);

        setLayout(new GridLayout());

        //both the menu bar and primary panel are added to the base panel
        mainframe.setJMenuBar(menuBar);
        add(primary);
    }
}