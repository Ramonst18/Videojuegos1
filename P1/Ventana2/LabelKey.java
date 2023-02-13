package P1.Ventana2;
import java.awt.event.*;

import javax.swing.*;

public class LabelKey extends JLabel implements Runnable {
    /*Nota: Ocupamos un focus para que el elemento este enfocado y pueda ser movido mediante un keylistener */

    int posX;

    //constructor
    public LabelKey(String texto){
        setText(texto);
        posX = this.getX();
    }

    //ejecucion del hilo
    public void run(){
        //agregamos el keyListenner
        this.addKeyListener(new KeyListener(){
            //clase anonima

            //METODOS QUE SE DEBEN DE PONER A FUERZAS EN KeyListener 
            public void keyTyped(KeyEvent e) {
                // MANEJO DE CARACTERES
                
            }

            
            public void keyPressed(KeyEvent e) {
                // Cuando se presiona una tecla
                
                //preguntamos si se presiono una tecla en espeficica, flecha derecha
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){

                    //mantenemos dentro de la ventana
                    if (posX<270) {
                        //realizamos el movimiento
                        posX += 10;
                        setBounds(posX, 40, 50, 25);
                    }

                }

                //movimiento a la izquierda, flecha izquierda
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    
                    //Lo mantenemos dentro de la ventana
                    if (posX > 0) {
                        
                        //realizamos el movimiento
                        posX -= 10;
                        setBounds(posX, 40, 50, 25);
                    }
                }
            }// end keyPressed

            
            public void keyReleased(KeyEvent e) {
                // Cuando se deja de presionar una tecla
                
            }
        });
    }//end run
}
