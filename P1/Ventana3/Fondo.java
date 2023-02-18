package P1.Ventana3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

        //Movemos el fondo
        setBounds(this.getX() - x, 0, 800, 300);
        //System.out.println(this.getX());
    }

    public ImageIcon getIcon(){
        return this.icon;
    }
    
}
