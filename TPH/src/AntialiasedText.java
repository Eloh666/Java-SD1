
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
 
/**
 * This class demonstrates how to use Rendering Hints to antialias text.
 */
public class AntialiasedText extends Applet {
           
	
    public void paint(Graphics g) {
    	
    	Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new File("matrix.ttf"));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
    	if(myFont != null)
    		myFont = myFont.deriveFont(25f);
    	else
    		myFont = new Font(Font.SERIF,Font.PLAIN,15);
 
        Graphics2D g2d = (Graphics2D)g;
 
        String text = "MATRIX";
        
        g2d.setFont(myFont);
        
        g2d.drawString(text, 20, 90);
    }
 
    public static void main(String[] args) {
 
        Frame f = new Frame("Antialiased Text Sample");
             
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
 
        f.add(new AntialiasedText());
        f.setSize(new Dimension(300, 180));
        f.setVisible(true);
    }
}