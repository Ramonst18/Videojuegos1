package P1.Ventana3;

import javax.swing.JPanel;
import java.awt.*;

public class Circulo extends JPanel{
    //atributos
    private int x = 0, y2 = 40, x2 = 60;

    public void moverCirculo(){
        x++;
    }

    public void moverLinea(){
        
        //if (y2 > (75)) {
        //    x2--;
        //}else{
        //    y2++;
        //}
    }

    public void paint(Graphics g){
        //usamos el contructor de la clase padre
        super.paint(g);
        this.setOpaque(false);

        //transformamos el g a Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        //Estas coordenadas seran las coordenadas en el JPanel
        g2d.fillOval(x, 0, 30, 30);

        //cambiamos el color de la linea
        g2d.setColor(Color.RED);

        //DIbujamos una linea de las posiciones x1,y1 a x2,y2
        g2d.drawLine(10, 40, x2, y2);

        //dibujamos un rectangulo sin relleno
        //g2d.drawRect(0, 50, 30, 30);

        //rectangulo con relleno
        //g2d.fillRect(0, 50, 30, 30);

        //rectangulo con bordes redondeados
        //g2d.fillRoundRect(0, 50, 30, 30, 20, 20);
    }// end paint
}