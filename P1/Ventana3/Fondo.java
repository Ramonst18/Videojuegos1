package P1.Ventana3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Graphics;

public class Fondo extends JLabel {
    //atributos
    private String url;
    private ImageIcon icon;

    public Fondo(String url){
        this.url = url;

        //Icon
        icon = new ImageIcon(this.getClass().getResource(url));
        setIcon(icon);
    }

    //Movimiento de la imagen
    public void mover_fondo(int x){

        //MOvemos el fondo
        setBounds(this.getX() - x, -150, 300, 600);
        
        //actualizamos el fondo
        //icon = new ImageIcon(this.getClass().getResource(url));
        //setIcon(icon);
        
        icon.paintIcon(this, getGraphics(), x, -150);
    }
    
}
