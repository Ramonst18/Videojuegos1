package P1.Ventana3;

import javax.swing.*;
import java.awt.event.*;

public class Imagen2 extends JLabel implements Runnable, KeyListener{
    
    //atributos
    private String url1, url2;
    private ImageIcon icon;
    private int posX = 10;
    private boolean runStatus = false, right = false, shift = false, up = true;


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

        //Escucha permanente
        while (true) {

            //MOVIMIENTO DEL PERSONAJE PARTE DERECHA
            if(this.right && this.shift){
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

                //Velocidad de refresco de pantalla
                try {
                    //Fotoprogramas d:
                    Thread.sleep(100);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }else if (this.right) {
                //incrementamos la posicion
                this.posX += 3;
                setBounds(posX, 100, 42, 42);
    
                //CAMBIOS ENTRE SPRITES DE MOVIMIENTO
                //preguntamos si es par o inpar, para cambiar los iconos
                if( this.posX % 2 != 0){
                    icon = new ImageIcon(this.getClass().getResource(this.url2));
                }else{
                    icon = new ImageIcon(this.getClass().getResource(this.url1));
                }
    
                setIcon(icon);

                //Velocidad de refresco de pantalla
                try {
                    //Fotoprogramas d:
                    Thread.sleep(200);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            
        }
        
    }

    
    //METODOS DEL KEYLISTENER
    public void keyPressed(KeyEvent e) {
        //obtenemos la posicion
        this.posX = this.getX();
        
        //DETECTAMOS LOS BOTONES DEL MOVIMIENTO DEL PERSONAJE
        if (runStatus) {

            //DERECHA
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                this.right = true;
            }
            
            //CORRER
            if(e.getKeyCode() == KeyEvent.VK_SHIFT){
                this.shift = true;
            } 
            
            //IZQUIERDA
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                this.right = true;
            }

            //SALTO
                
        }
        
    }

    public void keyReleased(KeyEvent e) {

        //BOTONES
        //DERECHA
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            shift = false;
        } 


        //ESTADO IDLE
        icon = new ImageIcon(this.getClass().getResource(this.url1));
        setIcon(icon);
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
