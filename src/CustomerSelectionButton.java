import java.awt.*;

//this is the default button for the customer selection panel
public class CustomerSelectionButton extends DefaultButton{
    CustomerSelectionButton(String buttonText){
        super(buttonText);
        setPreferredSize(new Dimension(120,120));
        setFont(new Font("SansSerif", Font.BOLD, 18));
    }
}