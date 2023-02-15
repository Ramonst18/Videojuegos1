package P1.Ventana3;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Imagen2 extends JLabel implements Runnable, KeyListener{
    
    //atributos
    private String url1, url2;
    private ImageIcon icon;
    private int posX = 10, posY = 0, posYSaltoBajo = 0, posYSaltoAlto = 0;
    private boolean runStatus = false, right = false, shift = false, up = false, left = false;
    private Fondo fondo;
    private File file ;

    //CONSTRUCTOR
    public Imagen2(String url1, String url2, Fondo fondo){
        this.url1 = url1;
        this.url2 = url2;
        this.fondo = fondo;
        file = new File(url1);

        //Icon
        icon = new ImageIcon(this.getClass().getResource(url1));
        setIcon(icon);
    }


    //HILO
    public void run(){
        /*Tarea, poner el fondo en la aplicacion, el fondo se estara moviendo cuando el personaje llegue al medio,
         * cuando se llegue al final del fondo, el fondo se detendra y el personaje se empezara a mover hasta la "meta"
         */


        //habilitamos el movimiento del personaje
        runStatus = true;

        //Escucha permanente
        while (true) {

            //obtenemos la posicion
            this.posX = this.getX();
            this.posY = this.getY();

            //System.out.println(this.right);
            //Verifica si existe el archivo
            file.exists();  //NECESARIO PPARA QUE SE EJECUTE EL JUEGO

            //MOVIMIENTO DEL PERSONAJE PARTE DERECHA
            if(this.right && this.shift){
                movimiento(11, 100);
            }else if (this.right) {
                movimiento(3, 200);
            }
            
            //MOVIMIENTO DEL PERSONAJE PARTE IZQUIERDA
            if(this.left && this.shift){
                movimiento(11, 100);
            }else if (this.left) {
                movimiento(3, 200);
            }
            
            //SALTO DEL PERSONAJE
            //Salto a la derecha
            if (this.right && this.up) {
                movimientoConSalto(3, 200, 50);
            } else if(this.left && this.up) {
                //Salto a la izquierda
                movimientoConSalto(3, 200, 50);
            } else if (this.up) {
                //Salto en el mismo lugar
                movimientoConSalto(0, 0, 50);
            }
        }
        
    }

    
    //METODOS DEL KEYLISTENER
    public void keyPressed(KeyEvent e) {
        
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
                this.left = true;
            }

            //SALTO
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.up = true;
            }
        }
        
    }

    public void keyReleased(KeyEvent e) {

        //BOTONES
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            shift = false;
        } 
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            this.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            this.up = false;

        }

        //ESTADO IDLE
        icon = new ImageIcon(this.getClass().getResource(this.url1));
        setIcon(icon);
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void movimiento(int desplezamiento, int velocidad){
        
        //Desplazamos el personaje en X
        if (this.right) {
            if (this.posX >= 150) {
                this.fondo.mover_fondo(desplezamiento);
            }else{
                this.posX += desplezamiento;
            }
        } else if(this.left){
            this.posX -= desplezamiento;
        }

        //Verificamos de que este a la mitad de la pantalla
        setBounds(posX, posY, 42, 42);
        

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
            Thread.sleep(velocidad);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void movimientoConSalto(int desplazamiento, int velocidad, int velocidadSalto) {

        // obtenemos la posicion del salto
        this.posYSaltoAlto = this.posY - 50;
        this.posYSaltoBajo = this.getY();

        // Subida
        for (this.posY = this.getY(); this.posY >= posYSaltoAlto; this.posY -= 5) {
            //Desplazamos el personaje en X
            if (this.right) {
                this.posX += desplazamiento;
            } else if(this.left){
                this.posX -= desplazamiento;
            }

            // incrementamos la posicion
            setBounds(posX, posY, 42, 42);

            // System.out.println(this.posY);

            // CAMBIOS ENTRE SPRITES DE MOVIMIENTO
            icon = new ImageIcon(this.getClass().getResource(this.url1));
            setIcon(icon);

            // Velocidad de refresco de pantalla
            try {
                // Fotoprogramas d:
                Thread.sleep(50);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        // BAJADA
        for (this.posY = this.getY(); this.posY < posYSaltoBajo; this.posY += 5) {
            //Desplazamos el personaje en X
            if (this.right) {
                this.posX += desplazamiento;
            } else if(this.left){
                this.posX -= desplazamiento;
            }
            
            // incrementamos la posicion
            setBounds(posX, posY, 42, 42);

            // System.out.println(this.posY);

            // CAMBIOS ENTRE SPRITES DE MOVIMIENTO
            icon = new ImageIcon(this.getClass().getResource(this.url1));
            setIcon(icon);

            // Velocidad de refresco de pantalla
            try {
                // Fotoprogramas d:
                Thread.sleep(50);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
