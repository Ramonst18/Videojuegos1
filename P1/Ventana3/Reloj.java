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
        /*
        //posicion abajo derecha
        if (y2 == 90 && x2 == 90) {
            System.out.println("abajo derecha");
            x = -1;
            y = 0;
            System.out.println(Math.sqrt((y2)^2+(x2)^2));
        }
        //Posicion abajo izquierda
        if (y2 == 90 && x2 == 0) {
            x=0;
            y=-1;
            System.out.println("Abajo izquierda");
            System.out.println(Math.sqrt((y2)^2+(x2)^2));
        }
        //posicion arriba izquierda
        if (y2 == 0 && x2 == 0) {
            System.out.println("Arriba izquierda");
            x=1;
            y=0;
            System.out.println(Math.sqrt((y2)^2+(x2)^2));
        }
        //posicion arriba derecha
        if (y2 == 0 && x2 == 90) {
            System.out.println("Arriba derecha");
            y=1;
            x=0;
            System.out.println(Math.sqrt((y2)^2+(x2)^2));
        }
        */

        //abajo derecha
        if (y2>67 && x2>67) {
            x = -1;
            y = 1;
        }

        //limitamos la mayor distancia de y
        if (y2==90) {
            y = 0;
        }

        //limitamos la mayor distancia de x
        if (x2 == 90) {
            x = 0;
        }

        //medio abajo
        if (x2 == 0 && y2 == 90) {
            y = -1;
        }

        //abajo izquierda
        if (x2<22 && y2 < 67) {
            System.out.println("aaa");
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

    }

}

