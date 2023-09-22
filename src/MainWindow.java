import javax.swing.*;

public class MainWindow extends JFrame{
    public BasePanel base;
    final int WINDOW_WIDTH = 900;
    final int WINDOW_HEIGHT = 500;
    public MainWindow() {
        //sets default width and height
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //sets title
        setTitle("Kansas City Energy Cooperative");
        //sets close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centers panel in window
        setLocationRelativeTo(null);

        base = new BasePanel(this);

        //sets the icon for the application
        ImageIcon img = new ImageIcon("src/Application_Icon.png");
        setIconImage(img.getImage());

        add(base);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
