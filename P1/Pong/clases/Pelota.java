package P1.Pong.clases;

import java.io.File;
import java.awt.geom.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Pelota extends JLabel implements Runnable {
    // atributos de clase
    private ImageIcon icon;
    private Pala[] palas = new Pala[2];
    private int posX, posY, XDirection = 1, YDirection = 1, tiempo = 80;
    private File file;
    private boolean pausar = false, stop = false, impulso = false;
    private JButton btnStop;

    // Constructor
    public Pelota(String url) {
        file = new File(url);
        icon = new ImageIcon(this.getClass().getResource(url));
        setIcon(icon);
    }

    public void run() {
        stop = false;
        this.posX = this.getX();
        this.posY = this.getY();
        randomDirection();

        while (true) {
            // necesario para que se siga ejecutando
            file.exists();

            // verificamos la posicion de la pelota para darle rebote
            if (posY < 2 || posY > 290) {
                YDirection *= -1;
            }

            // verificamos si pasaron a las palas para dar la puntuacion y comenzar el juego
            // de nuevo
            if (posX < 5) {
                palas[1].setScore(1);
                resetPosition();
                randomDirection();
                this.tiempo = 60;
                verificarGanador();
            } else if (posX > 280) {
                palas[0].setScore(1);
                resetPosition();
                randomDirection();
                this.tiempo = 60;
                verificarGanador();
            }

            interseccion();

            // Movimiento de la pelota
            if (this.XDirection > 0 && this.YDirection > 0) {
                moving(2, 2);
            } else if (this.XDirection > 0 && this.YDirection < 0) {
                moving(2, -2);
            } else if (this.XDirection < 0 && this.YDirection < 0) {
                moving(-2, -2);
            } else {
                moving(-2, 2);
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

    }

    private void moving(int x, int y) {
        posX += x;
        posY += y;

        this.setBounds(posX, posY, 8, 8);

        try {
            Thread.sleep(tiempo);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void interseccion() {
        /*
         * Metodo que nos informara si esta colisionando el personaje
         * con alguna superficie
         */

        // Sacamos las areas de las palas
        Area[] aPalas = new Area[palas.length];

        // obtenemos las dimensiones de todas las palas
        for (int i = 0; i < aPalas.length; i++) {
            aPalas[i] = new Area(palas[i].getBounds());
        }

        // area de la pelota
        Area aPelota = new Area(this.getBounds());

        // verificamos si alguna pala colisiona con la pelota
        for (int i = 0; i < aPalas.length; i++) {
            if (aPalas[i].intersects(aPelota.getBounds2D())) {
                this.XDirection *= -1;
                
                //Verificamos si la pala esta en modo golpe
                if (palas[i].getGolpe() && palas[i].getDashIndicator().getCarga() == 4) {

                    //verificamos que el tiempo no llegue ser menor de 5
                    if (this.tiempo > 10) {
                        this.tiempo -= 10;
                    }

                    palas[i].getDashIndicator().reanudarHilo(0);
                    palas[i].potenciar();
                }else if (this.tiempo >= 25) {
                    //limitamos que el tiempo no pase de tal valor
                    this.tiempo -= 5;
                }else if (this.tiempo > 10) {
                    this.tiempo -= 1;
                }

                //Verificamos que la pala que choca sea la IA
                if (palas[i].getPlayer().equals("P2") && Math.random() < 0.25) {
                    //Potenciaremos el impulso con una probabilidad del 25%
                    palas[i].potenciar();
                }
                System.out.println(this.tiempo);
            }
        }
    }

    public void setImpulso(boolean impulso) {
        this.impulso = impulso;
    }

    public void resetPosition() {
        this.setBounds(144, 150, 8, 8);
        this.posX = 144;
        this.posY = 150;
    }

    private void randomDirection() {
        // Direccion random para X
        if (Math.random() < 0.5) {
            this.XDirection *= -1;
        }

        // Direccion random para Y
        if (Math.random() < 0.5) {
            this.YDirection *= -1;
        }
    }

    private void verificarGanador(){
        //verificamos si el jugador uno gano
        if (palas[0].getScores().getScore() == 5) {
            this.btnStop.doClick();
            JOptionPane.showMessageDialog(null,"El ganador es el primer jugador");
            
        }else if (palas[1].getScores().getScore() == 5) {
            this.btnStop.doClick();
            JOptionPane.showMessageDialog(null,"El ganador es el segundo jugador");
        }
    }

    // GETTERS AND SETTERS
    public void setPalas(Pala[] palas) {
        this.palas = palas;
    }

    public void setBtnStop(JButton btnStop){
        this.btnStop = btnStop;
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
        this.tiempo = 80;
        notify();
    }
}
