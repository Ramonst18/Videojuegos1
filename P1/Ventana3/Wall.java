package P1.Ventana3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Wall extends JLabel {
    // atributos
    private String url;
    private ImageIcon icon;

    // Constructor
    public Wall(String url) {
        this.url = url;
        icon = new ImageIcon(this.getClass().getResource(url));
        setIcon(icon);
    }

    // Movimiento de la imagen
    public void mover_pared(int x) {

        // Movemos la pared
        setBounds(this.getX() - x, 110, 26, 26);
        // System.out.println(this.getX());
    }

}
