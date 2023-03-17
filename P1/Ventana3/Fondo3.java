package P1.Ventana3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Fondo3 extends JLabel {
    // atributos
    private String url;
    private ImageIcon icon;

    public Fondo3(String url) {
        this.url = url;

        // Icon
        icon = new ImageIcon(this.getClass().getResource(this.url));

        setIcon(icon);
    }

    // Movimiento de la imagen
    public void mover_fondo(int x) {

        // Movemos el fondo
        setBounds(this.getX() - x, 0, 510, 72);
        // System.out.println(this.getX());
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

}
