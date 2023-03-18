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
    private DashIndicator dashIndicator;
    private int score = 0, posY;
    private Scores scores;

    // Constructor
    public Pala(String url) {
        scores = new Scores();
        this.url = url;
        this.file = new File(this.url);
        this.icon = new ImageIcon(this.getClass().getResource(this.url));
        setIcon(icon);
    }// end Pala

    // Thread
    public void run() {
        runStatus = true;
        stop = false;
        
        this.posY = this.getY();

        // ejecucion continua del hilo
        while (true) {
            
            //PARA QUE SIGA EJECUTANDO
            file.exists();

            //MOVIMIENTO
            //arriba
            if (arriba && impulso && this.dashIndicator.getCarga() == 4) {
                //impulso hacia arriba
                movimientoRapido(-15);
                this.dashIndicator.reanudarHilo(0);
            }else if (arriba) {
                movimiento(-5);
            }

            //abajo
            if (abajo && impulso && this.dashIndicator.getCarga() == 4) {
                //impulso hacia abajo
                movimientoRapido(15);
                this.dashIndicator.reanudarHilo(0);
            }else if (abajo) {
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
                        // rompemos el ciclo 
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

        //limitamos por donde pasará la pala
        if (this.getY()+desplazamiento >= 0 && this.getY()+desplazamiento <= 260) {
            this.posY += desplazamiento;
        }

        this.setBounds(this.getX(), this.posY, 12, 42);

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void movimientoRapido(int desplazamiento){
        /*Metodo que realiza un movimiento rapido de la pala por la ventana */
        //limitamos por donde pasará la pala
        if (this.getY()+desplazamiento >= 0 && this.getY()+desplazamiento <= 260) {
            this.posY += desplazamiento;
        }

        this.setBounds(this.getX(), this.posY, 12, 42);

        try {
            Thread.sleep(10);
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

            //impulso
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                this.impulso = true;
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

        //impulso
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            this.impulso = false;
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
        this.dashIndicator.stopHilo();
        notify();
    }

    public void resetPala(){
        //Posicion
        this.setBounds(this.getX(), 130, 12, 42);

        //Puntuacion
        this.score = 0;
        this.scores.actualizarPuntaje(score);
    }

    //GETTERS AND SETTERS
    public Scores getScores(){
        return this.scores;
    }

    public DashIndicator getDashIndicator(){
        return this.dashIndicator;
    }

    public void setDashIndicator(DashIndicator dashIndicator){
        this.dashIndicator = dashIndicator;
    }

}
