package P1.Ventana3;


import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.geom.*;


public class Monito extends JLabel implements Runnable{
    private String url1, url2;
    private ImageIcon icon;
    private File file;
    private boolean moveStatus = false, pausar;

    public Monito(String url1, String url2){
        this.url1 = url1;
        this.url2 = url2;
        file = new File(url1);

        // Icon
        icon = new ImageIcon(this.getClass().getResource(url1));
        setIcon(icon);
    }

    public void run(){
        for (int x = 10; x < 200; x += 3) {

            //actualizamos los iconos
            if (moveStatus) {
                icon = new ImageIcon(this.getClass().getResource(url1));
                moveStatus = false;
            }else{
                icon = new ImageIcon(this.getClass().getResource(url2));
                moveStatus = true;
            }

            //actualizamos el icono y damos el movimiento
            setIcon(icon);
            setBounds(x, 10, 42, 42);

            //frame d:
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }

            //sincronizacion
            try {
                synchronized(this){
                    //Porcion de codigo sincronizada
                    while (pausar) {
                        //si pausar es verdadero, el hilo estara en espera
                        wait();
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    //METODOS SINCRONIZADOS
    synchronized void pausarHilo(){
        pausar = true;
        
    }
    synchronized void reanudarHilo(){
        
    }
    synchronized void stopHilo(){
        
    }
}
