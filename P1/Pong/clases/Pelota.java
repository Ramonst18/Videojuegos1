package P1.Pong.clases;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pelota extends JLabel implements Runnable{
    //atributos de clase
    private ImageIcon icon;

    //Constructor
    public Pelota(String url){
        icon = new ImageIcon(this.getClass().getResource(url));
        setIcon(icon);
    }

    
    public void run() {
        
    }

}
