package P1.Pong.clases;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Fondo extends JLabel{
    //atributos de la clase
    private String url;
    private ImageIcon icon;

    //Constructor
    public Fondo(String url){
        this.url = url;
        icon = new ImageIcon(this.getClass().getResource(this.url));
        setIcon(icon);
    }

}
