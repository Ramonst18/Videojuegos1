package P1.Ventana3;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.geom.*;

public class Imagen2 extends JLabel implements Runnable, KeyListener{
    
    //atributos
    private String url1, url2;
    private ImageIcon icon;
    private int posX = 10, posY = 0, posYSaltoBajo = 0, posYSaltoAlto = 0;
    private boolean runStatus = false, right = false, shift = false, up = false, left = false, changeImg = false;
    private Fondo fondo;
    private File file ;
    Wall [] walls;

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
        //habilitamos el movimiento del personaje
        runStatus = true;

        //Escucha permanente
        while (true) {

            //obtenemos la posicion
            this.posX = this.getX();
            this.posY = this.getY();

            //Verifica si existe el archivo
            file.exists();  //NECESARIO PPARA QUE SE EJECUTE EL JUEGO

            //Si llegase a colisionar
            if (interseccion()) {
                //terminamos el ciclo, terminamos la ejecucion del programa
                break;
            }

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
            if (this.right && this.shift && this.up) {
                //Salto a la derecha con correr
                movimientoConSalto(11, 100, 50);
            } else if (this.right && this.up) {
                //salto a la derecha
                movimientoConSalto(3, 200, 50);
            } else if (this.left && this.shift && this.up) {
                //salto a la izquierda con correr
                movimientoConSalto(11, 100, 50);
            } else if(this.left && this.up) {
                //Salto a la izquierda
                movimientoConSalto(3, 200, 50);
            } else if (this.up) {
                //Salto en el mismo lugar
                movimientoConSalto(0, 0, 50);
            }

        }
        
    }

    private boolean interseccion(){
        //Variable donde tendremos las areas de los bloques
        Area [] areaWalls = new Area[walls.length];

        //variable de colision
        boolean colisiona = false;
                
        //OBtenemos el area de las paredes
        for (int i = 0; i < areaWalls.length; i++) {
            areaWalls[i] = new Area(walls[i].getBounds());
        }

        //obtenemos el area del personaje
        Area areaMario = new Area(this.getBounds());

        //verificamos si colisiona con algun bloque
        for (int i = 0; i < areaWalls.length; i++) {
            if (areaWalls[i].intersects(areaMario.getBounds2D())) {
                colisiona = true;
            }
        }

        //regresamos la comparacion de la inteerseccion del area de la pared al del mario, o sea si colisiona el mario con la pared
        return colisiona; //areaWall1.intersects(areaMario.getBounds2D());
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
            if (this.posX >= 150 && (this.fondo.getX() * -1 ) < (this.fondo.getIcon().getIconWidth() - 300) ) {
                //verificamos que este despues de la mitad de la ventana y este en un lugar menor a la dimencion del icon menos la dimension de la ventana
                this.fondo.mover_fondo(desplezamiento);

                //desplazamos los bloques
                for (int i = 0; i < walls.length; i++) {
                    this.walls[i].mover_pared(desplezamiento);
                }
            }else{
                this.posX += desplezamiento;
            }
        } else if(this.left){
            if (this.posX <= 150 && this.fondo.getX() < 0) {
                //verificamos si la posicion del personaje es menos de la mitad de la pantalla y la posicion del fondo es menor de 0 para realizar el movimiento
                this.fondo.mover_fondo(desplezamiento * -1);
                
                //desplazamos los bloques
                for (int i = 0; i < walls.length; i++) {
                    this.walls[i].mover_pared(desplezamiento * -1);
                }
            }else{
                this.posX -= desplezamiento;
            }
        }

        //Verificamos de que este a la mitad de la pantalla
        setBounds(posX, posY, 42, 42);
        

        //CAMBIOS ENTRE SPRITES DE MOVIMIENTO
        //preguntamos si es par o inpar, para cambiar los iconos
        if( changeImg ){
            icon = new ImageIcon(this.getClass().getResource(this.url2));
            changeImg = false;
        }else{
            icon = new ImageIcon(this.getClass().getResource(this.url1));
            changeImg = true;
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
                if (this.posX >= 150 && (this.fondo.getX() * -1 ) < (this.fondo.getIcon().getIconWidth() - 300) ) {
                    //verificamos que este despues de la mitad de la ventana y este en un lugar menor a la dimencion del icon menos la dimension de la ventana
                    this.fondo.mover_fondo(desplazamiento);

                    //desplazamos los bloques
                    for (int i = 0; i < walls.length; i++) {
                        this.walls[i].mover_pared(desplazamiento);
                    }
                }else{
                    this.posX += desplazamiento;
                }
            } else if(this.left){
                if (this.posX <= 150 && this.fondo.getX() < 0) {
                    //verificamos si la posicion del personaje es menos de la mitad de la pantalla y la posicion del fondo es menor de 0 para realizar el movimiento
                    this.fondo.mover_fondo(desplazamiento * -1);

                    //desplazamos los bloques
                    for (int i = 0; i < walls.length; i++) {
                        this.walls[i].mover_pared(desplazamiento * -1);
                    }
                }else{
                    this.posX -= desplazamiento;
                }
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
        for (this.posY = this.getY(); this.posY <= posYSaltoBajo; this.posY += 5) {
            //Desplazamos el personaje en X
            if (this.right) {
                if (this.posX >= 150 && (this.fondo.getX() * -1 ) < (this.fondo.getIcon().getIconWidth() - 300) ) {
                    //verificamos que este despues de la mitad de la ventana y este en un lugar menor a la dimencion del icon menos la dimension de la ventana
                    this.fondo.mover_fondo(desplazamiento);

                    //desplazamos los bloques
                    for (int i = 0; i < walls.length; i++) {
                        this.walls[i].mover_pared(desplazamiento);
                    }
                }else{
                    this.posX += desplazamiento;
                }
            } else if(this.left){
                if (this.posX <= 150 && this.fondo.getX() < 0) {
                    //verificamos si la posicion del personaje es menos de la mitad de la pantalla y la posicion del fondo es menor de 0 para realizar el movimiento
                    this.fondo.mover_fondo(desplazamiento * -1);

                    //desplazamos los bloques
                    for (int i = 0; i < walls.length; i++) {
                        this.walls[i].mover_pared(desplazamiento * -1);
                    }
                }else{
                    this.posX -= desplazamiento;
                }
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
        System.out.println(this.posY); 
    }

}
