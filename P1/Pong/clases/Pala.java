package P1.Pong.clases;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Pala extends JLabel implements Runnable, KeyListener {
    // atributos de la clase
    private File file;
    private Pelota pelota;
    private String url, player, urlGifPotenciado;
    private boolean runStatus = false, arriba = false, abajo = false, pausar = false, stop = false,
            impulso = false, golpe = false;
    private ImageIcon icon;
    private DashIndicator dashIndicator;
    private int score = 0, posY;
    private Scores scores;

    // Constructor
    public Pala(String url, String player, String urlGifPotenciado) {
        scores = new Scores();
        this.player = player;
        this.url = url;
        this.urlGifPotenciado = urlGifPotenciado;
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

            // PARA QUE SIGA EJECUTANDO
            file.exists();

            // MOVIMIENTO
            if (player.equals("P1")) {
                // arriba
                if (arriba && impulso && this.dashIndicator.getCarga() == 4) {
                    // impulso hacia arriba
                    movimientoRapido(-20);
                    this.dashIndicator.reanudarHilo(0);
                } else if (arriba) {
                    movimiento(-5);
                }

                // abajo
                if (abajo && impulso && this.dashIndicator.getCarga() == 4) {
                    // impulso hacia abajo
                    movimientoRapido(20);
                    this.dashIndicator.reanudarHilo(0);
                } else if (abajo) {
                    movimiento(5);
                }

                // Golpe

            } else {
                // Movimiento de la IA
                if (pelota.getY() > this.getY()) {
                    movimiento(5);
                } else if (pelota.getY() < this.getY()) {// arriba
                    movimiento(-5);
                }

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
        /* Metodo que realiza el movimiento de la pala por la ventana */

        // limitamos por donde pasará la pala
        if (this.getY() + desplazamiento >= 0 && this.getY() + desplazamiento <= 260) {
            this.posY += desplazamiento;
        }

        this.setBounds(this.getX(), this.posY, 12, 42);

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void movimientoRapido(int desplazamiento) {
        /* Metodo que realiza un movimiento rapido de la pala por la ventana */
        // limitamos por donde pasará la pala
        if (this.getY() + desplazamiento >= 0 && this.getY() + desplazamiento <= 260) {
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
            // verificamos si es el player 1 o el 2
            if (player.equals("P1")) {
                // arriba
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.arriba = true;
                }

                // abajo
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.abajo = true;
                }

                // impulso
                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    this.impulso = true;
                }

                // Golpe
                if (e.getKeyCode() == KeyEvent.VK_X && this.dashIndicator.getCarga() == 4) {
                    potenciar();
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        // verificamos si es el player 1 o el 2
        if (player.equals("P1")) {
            // arriba
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.arriba = false;
            }

            // abajo
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.abajo = false;
            }

            // impulso
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                this.impulso = false;
            }

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

    public void resetPala() {
        // Posicion
        this.setBounds(this.getX(), 130, 12, 42);

        // Puntuacion
        this.score = 0;
        this.scores.actualizarPuntaje(score);

        // golpe
        this.golpe = false;
        icon = new ImageIcon(this.getClass().getResource(this.url));
        setIcon(icon);
    }

    // GETTERS AND SETTERS
    public Scores getScores() {
        return this.scores;
    }

    public void setScore(int score) {
        this.score += score;
        this.scores.actualizarPuntaje(this.score);
    }

    public DashIndicator getDashIndicator() {
        return this.dashIndicator;
    }

    public void setDashIndicator(DashIndicator dashIndicator) {
        this.dashIndicator = dashIndicator;
    }

    public void setPelota(Pelota pelota) {
        this.pelota = pelota;
    }

    public boolean getGolpe() {
        return this.golpe;
    }

    public void setGolpe(boolean golpe) {
        this.golpe = golpe;
    }

    public void potenciar() {
        if (this.golpe) {
            // Modo normal
            this.golpe = false;
            icon = new ImageIcon(this.getClass().getResource(this.url));
            setIcon(icon);
        } else {
            // Modo potenciado
            this.golpe = true;
            icon = new ImageIcon(this.getClass().getResource(this.urlGifPotenciado));
            setIcon(icon);
        }
    }

    public String getPlayer(){
        return this.player;
    }
}
