package P1.Ventana3;

import javax.swing.JPanel;
import java.awt.*;

public class Circulo extends JPanel{
    //atributos
    private int x = 0;

    public void moverCirculo(){
        x++;
    }

    public void paint(Graphics g){
        //usamos el contructor de la clase padre
        super.paint(g);

        //transformamos el g a Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        //Estas coordenadas seran las coordenadas en el JPanel
        g2d.fillOval(x, 0, 30, 30);
    }// end paint
}