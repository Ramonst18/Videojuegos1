package P1.Pong.clases;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DashIndicator extends JLabel implements Runnable {
    /*
     * Notitas :D
     * url1 es para cuando el indicador este completamente lleno
     * url2 es un cuarto
     * url3 es un medio
     * url4 es tres cuartos del indicador
     */

    // Atributos de clase
    private String url1, url2, url3, url4;
    private boolean pausar = true, stop = false;
    private ImageIcon icon;
    private int carga = 4;

    // Constructor
    public DashIndicator(String url1, String url2, String url3, String url4) {
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        this.url4 = url4;

        this.icon = new ImageIcon(this.getClass().getResource(this.url4));
        setIcon(icon);
    }

    // hilo
    public void run() {
        this.stop = false;

        while (true) {

            // aumentamos la carga y actualizamos el icono
            if (this.carga < 4) {
                this.carga++;
                actualizarIcon();
            }else{
                this.pausarHilo();
            }

            // TIEMPO DE ESPERA ENTRE CARGAS
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
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

    private void actualizarIcon() {
        if (this.carga == 1) {
            this.icon = new ImageIcon(this.getClass().getResource(this.url1));
            setIcon(icon);
        } else if (this.carga == 2) {
            this.icon = new ImageIcon(this.getClass().getResource(this.url2));
            setIcon(icon);
        } else if (this.carga == 3) {
            this.icon = new ImageIcon(this.getClass().getResource(this.url3));
            setIcon(icon);
        } else if (this.carga == 4) {
            this.icon = new ImageIcon(this.getClass().getResource(this.url4));
            setIcon(icon);
        }
    }

    public int getCarga(){
        return this.carga;
    }

    // METODOS SINCRONIZADOS
    synchronized void pausarHilo() {
        pausar = true;
    }

    synchronized void reanudarHilo(int carga) {
        pausar = false;
        this.carga = carga;
        notify(); // Despertamos al thread
    }

    synchronized void stopHilo() {
        stop = true;
        pausar = false;
        this.carga = 4;
        actualizarIcon();
        notify();
    }

}