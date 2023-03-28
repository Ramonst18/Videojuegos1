package P1.Ventana3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Reloj extends JPanel implements Runnable {
    // Atributos
    private int x2 = 90, y2 = 45;
    private int x = 0, y = 1;

    public void run() {

        while (true) {

            //movimiento de la linea
            this.moverLinea();
            this.repaint();

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    private void moverLinea() {
        //posicion abajo derecha
        if (y2 == 90 && x2 == 90) {
            System.out.println("abajo derecha");
            x = -1;
            y = 0;
        }
        //Posicion abajo izquierda
        if (y2 == 90 && x2 == 0) {
            x=0;
            y=-1;
            System.out.println("Abajo izquierda");
        }
        //posicion arriba izquierda
        if (y2 == 0 && x2 == 0) {
            System.out.println("Arriba izquierda");
            x=1;
            y=0;

        }
        //posicion arriba derecha
        if (y2 == 0 && x2 == 90) {
            System.out.println("Arriba derecha");
            y=1;
            x=0;
        }

        x2 += x;
        y2 += y;
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

    }

}

