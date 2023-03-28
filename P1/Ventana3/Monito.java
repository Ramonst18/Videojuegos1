package P1.Ventana3;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Monito extends JLabel implements Runnable, KeyListener {
    private String url1, url2;
    private ImageIcon icon;
    private File file;
    private boolean moveStatus = false, pausar, stop, runStatus = false, bandera = true;
    Circulo c1;

    public Monito(String url1, String url2) {
        this.url1 = url1;
        this.url2 = url2;
        file = new File(url1);

        // Icon
        icon = new ImageIcon(this.getClass().getResource(url1));
        setIcon(icon);
    }

    public void run() {
        runStatus = true;
        stop = false;

        file.exists();

        for (int x = 10; x < 200; x += 3) {

            // actualizamos los iconos
            if (moveStatus) {
                icon = new ImageIcon(this.getClass().getResource(url1));
                moveStatus = false;
            } else {
                icon = new ImageIcon(this.getClass().getResource(url2));
                moveStatus = true;
            }

            // actualizamos el icono y damos el movimiento
            setIcon(icon);
            setBounds(x, 10, 42, 42);

            // frame d:
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }

            // sincronizacion, se tiene que poner cada vez lo de abajo cuando se usa un
            // THread.sleep
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
            }

            //c1.moverCirculo();
            //c1.moverLinea();
            //c1.repaint();
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

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (runStatus) {
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                if (bandera) {
                    pausarHilo();
                    bandera = false;
                } else {
                    reanudarHilo();
                    bandera = true;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_X) {
                stopHilo();
                bandera = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
