package MainGUI;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    final int WINDOW_WIDTH = 900;
    final int WINDOW_HEIGHT = 500;
    public GUI() {
        //sets default width and height
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //sets title
        setTitle("Kansas City Energy Cooperative");
        //sets close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centers panel in window
        setLocationRelativeTo(null);

        //sets the icon for the application
        ImageIcon img = new ImageIcon("src/Application_Icon.png");
        setIconImage(img.getImage());

        buildFrame();

        setVisible(true);
    }
    private void buildFrame(){
        PrimaryPanel primary = new PrimaryPanel();

        //an instance of the primary panel is passed to the menu bar constructor
        //used to call the primary panel sign-put warning
        MenuBar menuBar = new MenuBar(primary);

        //an instance of menu bar is set with a primary panel setter
        //this allows primary panel to change the visibility and text of the menu bar
        primary.setMenuBarInstance(menuBar);

        setLayout(new GridLayout());

        //both the menu bar and primary panel are added to the base panel
        setJMenuBar(menuBar);
        add(primary);
    }
    public static void main(String[] args) {
        new GUI();
    }
}
