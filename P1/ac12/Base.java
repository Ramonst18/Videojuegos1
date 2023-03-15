package P1.ac12;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Base extends JLabel{
    
    // atributos
    private String url;
    private ImageIcon icon;

    public Base(String url) {
        this.url = url;

        // Icon
        icon = new ImageIcon(this.getClass().getResource(this.url));

        setIcon(icon);
    }

    // Movimiento de la imagen
    public void mover_base(int x) {
        // Movemos la base
        setBounds(this.getX() - x, this.getY(), 161, 27);
        // System.out.println(this.getX());
    }

}
