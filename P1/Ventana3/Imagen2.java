package P1.Ventana3;

import javax.swing.*;
import java.awt.event.*;

public class Imagen2 extends JLabel implements Runnable, KeyListener{
    
    //atributos
    private String url1, url2;
    private ImageIcon icon;
    private int posX = 10;
    private boolean runStatus = false;

    //CONSTRUCTOR
    public Imagen2(String url1, String url2){
        this.url1 = url1;
        this.url2 = url2;

        //Icon
        icon = new ImageIcon(this.getClass().getResource(url1));
        setIcon(icon);
    }


    //HILO
    public void run(){

        //habilitamos el movimiento del personaje
        runStatus = true;

    }

    
    //METODOS DEL KEYLISTENER
    public void keyPressed(KeyEvent e) {
        //obtenemos la posicion
        this.posX = this.getX();
        
        //MOVIMIENTO DEL PERSONAJE
        if (runStatus) {

            //MOVIMIENTO EN CORRER
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                //incrementamos la posicion
                this.posX += 11;
                setBounds(posX, 100, 42, 42);

                //CAMBIOS ENTRE SPRITES DE MOVIMIENTO
                //preguntamos si es par o inpar, para cambiar los iconos
                if( this.posX % 2 != 0){
                    icon = new ImageIcon(this.getClass().getResource(this.url2));
                }else{
                    icon = new ImageIcon(this.getClass().getResource(this.url1));
                }

                setIcon(icon);
            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                //MOVIMIENTO EN CAMINATA

                //incrementamos la posicion
                this.posX += 1;
                setBounds(posX, 100, 42, 42);

                //CAMBIOS ENTRE SPRITES DE MOVIMIENTO
                //preguntamos si es par o inpar, para cambiar los iconos
                if( this.posX % 2 != 0){
                    icon = new ImageIcon(this.getClass().getResource(this.url2));
                }else{
                    icon = new ImageIcon(this.getClass().getResource(this.url1));
                }

                setIcon(icon);
            }

            
                
        }
        
    }

    public void keyReleased(KeyEvent e) {
        
        //ESTADO IDLE
        icon = new ImageIcon(this.getClass().getResource(this.url1));
        setIcon(icon);
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
