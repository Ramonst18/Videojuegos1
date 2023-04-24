package P1.Ventana3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Reloj extends JPanel implements Runnable {
    // Atributos
    private int x2 = 90, y2 = 45;
    private int x = -1, y = 1;

    public void run() {

        while (true) {

            //movimiento de la linea
            this.moverLinea();
            this.repaint();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    private void moverLinea() {
        
        //derecha
        if (y2 == 45 && x2 == 90) {
            
            x = -1;
            y = 1;
            
        }

        //abajo
        if (y2 == 90 && x2 == 45) {
            x = -1;
            y = -1;
        }
        //izquierda
        if (y2 == 45 && x2 == 0) {
            x = 1;
            y = -1;
        }

        //arriba
        if (y2 == 0 && x2 == 45) {
            x = 1;
            y = 1;
        }

        x2 += x;
        y2 += y;
        System.out.println();
        System.out.println(x2);
        System.out.println(y2);
    }

    public void paint(Graphics g) {
        // usamos el contructor de la clase padre
        super.paint(g);
        this.setOpaque(false);

        // transformamos el g a Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // cambiamos el color de la linea
        g2d.setColor(Color.RED);

        // DIbujamos una linea de las posiciones x1,y1 a x2,y2
        g2d.drawLine(45, 45, x2, y2);
        g2d.drawOval(0, 0, 90, 90);
    }

}

