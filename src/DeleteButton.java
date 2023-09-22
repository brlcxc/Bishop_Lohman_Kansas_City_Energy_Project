import java.awt.*;

//this is the standard delete button with a standard customer size and text from the normal caution button
public class DeleteButton extends CautionButton{
    DeleteButton(String buttonText){
        super(buttonText);
        setPreferredSize(new Dimension(120,120));
        setFont(new Font("SansSerif", Font.BOLD, 18));
    }
}
