package P1.Pong.clases;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.geom.*;

public class Pala extends JLabel implements Runnable, KeyListener {
    // atributos de la clase
    private File file;
    private String url;
    private boolean runStatus = false, arriba = false, abajo = false,
            impulso = false, golpe = false;
    private ImageIcon icon;
    private int score = 0;

    // Constructor
    public Pala(String url) {
        this.url = url;
        this.file = new File(this.url);
        this.icon = new ImageIcon(this.getClass().getResource(this.url));
        setIcon(icon);
    }// end Pala

    // Thread
    public void run() {
        runStatus = true;

        // ejecucion continua del hilo
        while (true) {
            
            //MOVIMIENTO
            //arriba
            if (arriba) {
                movimiento(5);
            }

            //abajo
            if (abajo) {
                movimiento(-5);
            }
        }
    }// end run

    // MOVIMIENTO DE LA PALA
    private void movimiento(int desplazamiento) {
        /*Metodo que realiza el movimiento de la pala por la ventana */

        this.setBounds(this.getX(), this.getY() + desplazamiento, 12, 42);

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // KEYLISTENER
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        // si se esta ejecutando
        if (runStatus) {
            // arriba
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.arriba = true;
            }

            // abajo
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.abajo = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        // arriba
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.arriba = false;
        }

        // abajo
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.abajo = false;
        }
    }

}
