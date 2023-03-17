package P1.Pong.clases;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.geom.*;

public class Pala extends JLabel implements Runnable, KeyListener {
    // atributos de la clase
    private File file;
    private String url;
    private boolean runStatus = false, arriba = false, abajo = false, pausar = false, stop = false,
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
        stop = false;

        // ejecucion continua del hilo
        while (true) {
            
            //PARA QUE SIGA EJECUTANDO
            file.exists();

            //MOVIMIENTO
            //arriba
            if (arriba) {
                movimiento(-5);
            }

            //abajo
            if (abajo) {
                movimiento(5);
            }

            // PAUSA Y RESUMEN DE LA PALA
            try {
                synchronized (this) {
                    // Porcion de codigo sincronizada
                    while (pausar) {
                        // si pausar es verdadero, el hilo estara en espera
                        wait();
                    }

                    if (stop) {
                        // rompemos el ciclo del for
                        break;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
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

    // METODOS SINCRONIZADOS
    synchronized void pausarHilo() {
        pausar = true;
    }

    synchronized void reanudarHilo() {
        pausar = false;
        notify(); // Despertamos al thread
    }

    synchronized void stopHilo() {
        stop = true;
        pausar = false;
        notify();
    }
}
