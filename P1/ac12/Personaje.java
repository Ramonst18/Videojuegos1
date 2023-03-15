package P1.ac12;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.geom.*;

public class Personaje extends JLabel implements Runnable, KeyListener {

    // atributos
    private String url1, url2;
    private ImageIcon icon;
    private int posX = 10, posY = 0, posYSaltoAlto = 0;
    private boolean runStatus = false, right = false, shift = false, up = false, left = false, changeImg = false,
            colisionSuperficie = false, gameOver = false, pausar;
    private Fondo3 fondo;
    private File file;
    Base [] bases;

    // CONSTRUCTOR
    public Personaje(String url1, String url2, Fondo3 fondo) {
        this.url1 = url1;
        this.url2 = url2;
        this.fondo = fondo;
        file = new File(url1);

        // Icon
        icon = new ImageIcon(this.getClass().getResource(url1));
        setIcon(icon);
    }

    // HILO
    public void run() {
        // habilitamos el movimiento del personaje
        runStatus = true;
        //pausar = false;
        // Escucha permanente
        while (true && !gameOver) {
            // obtenemos la posicion
            this.posX = this.getX();
            this.posY = this.getY();

            // Verifica si existe el archivo
            file.exists(); // NECESARIO PPARA QUE SE EJECUTE EL JUEGO

            // Gravedad del personaje
            gravedad(2, 100);

            // MOVIMIENTO DEL PERSONAJE PARTE DERECHA
            if (this.right && this.shift) {
                movimiento(6, 100);
            } else if (this.right) {
                movimiento(2, 100);
            }

            // MOVIMIENTO DEL PERSONAJE PARTE IZQUIERDA
            if (this.left && this.shift) {
                movimiento(6, 100);
            } else if (this.left) {
                movimiento(2, 100);
            }

            // SALTO DEL PERSONAJE
            if (this.right && this.shift && this.up) {
                // Salto a la derecha con correr
                movimientoConSalto(6, 100, 50);
            } else if (this.right && this.up) {
                // salto a la derecha
                movimientoConSalto(2, 100, 50);
            } else if (this.left && this.shift && this.up) {
                // salto a la izquierda con correr
                movimientoConSalto(6, 100, 50);
            } else if (this.left && this.up) {
                // Salto a la izquierda
                movimientoConSalto(2, 100, 50);
            } else if (this.up) {
                // Salto en el mismo lugar
                movimientoConSalto(0, 100, 50);
            }

            
            //Pausa y resume del monito
            try {
                synchronized (this) {
                    // Porcion de codigo sincronizada
                    while (pausar) {
                        // si pausar es verdadero, el hilo estara en espera
                        wait();
                    }

                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }

        }

    }

    private void gravedad(int presion, int time) {
        interseccion();

        // Verificamos si no tiene un objeto en la parte de abajo
        if (!this.colisionSuperficie) {
            posY += presion;
            setBounds(getX(), posY, 42, 42);

            //Comprobamos si llega a superar tal posicion de caida para mandar la perdida
            if (posY >= 120) {
                gameOver = true;

                //Cambiar un label en la ventana que indique que se murio el personaje
                JOptionPane.showMessageDialog(null, "Te moriste");
            }

            try {
                Thread.sleep(time);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private void interseccion() {
        /*Metodo que nos informara si esta colisionando el personaje
         * con alguna superficie
         */

        //Sacamos las areas de las bases
        Area [] aBases = new Area[bases.length];

        //obtenemos las dimensiones de todas las bases
        for (int i = 0; i < aBases.length; i++) {
            aBases[i] = new Area(bases[i].getBounds());
        }

        // area del personaje
        Area aMario = new Area(this.getBounds());

        //verificamos si alguna base colisiona con el personaje
        for (int i = 0; i < aBases.length; i++) {
            if (aBases[i].intersects(aMario.getBounds2D())) {

                //verificamos si colisiona con la parte de arriba
                /*if (aBases[i].getBounds2D().getMinY() + 1 == aMario.getBounds2D().getMaxY()) {
                    System.out.println("Arriba");
                }*/
                
                this.colisionSuperficie = true;
                break;
            } else{
                this.colisionSuperficie = false;
            }
        }
    }

    // METODOS DEL KEYLISTENER
    public void keyPressed(KeyEvent e) {

        // DETECTAMOS LOS BOTONES DEL MOVIMIENTO DEL PERSONAJE
        if (runStatus) {

            // DERECHA
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.right = true;
            }

            // CORRER
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                this.shift = true;
            }

            // IZQUIERDA
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.left = true;
            }

            // SALTO
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.up = true;
            }
        }

    }

    public void keyReleased(KeyEvent e) {

        // BOTONES
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.up = false;

        }

        // ESTADO IDLE
        icon = new ImageIcon(this.getClass().getResource(this.url1));
        setIcon(icon);
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void movimiento(int desplezamiento, int velocidad) {

        // Desplazamos el personaje en X
        if (this.right) {
            if (this.posX >= 150 && (this.fondo.getX() * -1) < (this.fondo.getIcon().getIconWidth() - 300)) {
                // verificamos que este despues de la mitad de la ventana y este en un lugar
                // menor a la dimencion del icon menos la dimension de la ventana
                this.fondo.mover_fondo(desplezamiento);
                for (int i = 0; i < bases.length; i++) {
                    
                    this.bases[i].mover_base(desplezamiento);
                }
            } else {
                this.posX += desplezamiento;
            }
        } else if (this.left) {
            if (this.posX <= 150 && this.fondo.getX() < 0) {
                // verificamos si la posicion del personaje es menos de la mitad de la pantalla
                // y la posicion del fondo es menor de 0 para realizar el movimiento
                this.fondo.mover_fondo(desplezamiento * -1);
                
                for (int i = 0; i < bases.length; i++) {
                            
                    this.bases[i].mover_base(desplezamiento * -1);
                }
            } else {
                this.posX -= desplezamiento;
            }
        }

        // Verificamos de que este a la mitad de la pantalla
        setBounds(posX, posY, this.getWidth(), this.getHeight());

        actualizarSprite();

        // Velocidad de refresco de pantalla
        try {
            // Fotoprogramas d:
            Thread.sleep(velocidad);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void movimientoConSalto(int desplazamiento, int velocidad, int velocidadSalto) {

        // obtenemos la posicion del salto
        this.posYSaltoAlto = this.posY - 20;

        // preguntamos si esta colisionando con alguna superficie
        if (this.colisionSuperficie) {

            // Subida
            for (this.posY = this.getY(); this.posY >= posYSaltoAlto; this.posY -= 5) {
                // Desplazamos el personaje en X
                if (this.right) {
                    if (this.posX >= 150 && (this.fondo.getX() * -1) < (this.fondo.getIcon().getIconWidth() - 300)) {
                        // verificamos que este despues de la mitad de la ventana y este en un lugar
                        // menor a la dimencion del icon menos la dimension de la ventana
                        this.fondo.mover_fondo(desplazamiento);

                        for (int i = 0; i < bases.length; i++) {
                            
                            this.bases[i].mover_base(desplazamiento);
                        }
                    } else {
                        this.posX += desplazamiento;
                    }
                } else if (this.left) {
                    if (this.posX <= 150 && this.fondo.getX() < 0) {
                        // verificamos si la posicion del personaje es menos de la mitad de la pantalla
                        // y la posicion del fondo es menor de 0 para realizar el movimiento
                        this.fondo.mover_fondo(desplazamiento * -1);

                        for (int i = 0; i < bases.length; i++) {
                            
                            this.bases[i].mover_base(desplazamiento * -1);
                        }
                    } else {
                        this.posX -= desplazamiento;
                    }
                }

                // incrementamos la posicion
                setBounds(posX, posY, 42, 42);

                //NO SE REALIZARAN CAMBIOS ENTRE LOS SPRITE CON LOS SALTOS, YA SE HACEN CON EL MOVIMIENTO NORMAL

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

    public void actualizarSprite() {
        /* Metodo que realiza el cambio entre los sprites */
        // CAMBIOS ENTRE SPRITES DE MOVIMIENTO
        if (changeImg) {
            icon = new ImageIcon(this.getClass().getResource(this.url2));
            changeImg = false;
        } else {
            icon = new ImageIcon(this.getClass().getResource(this.url1));
            changeImg = true;
        }

        setIcon(icon);
    }

    // METODOS SINCRONIZADOS
    synchronized void pausarHilo() {
        pausar = true;

    }

    synchronized void reanudarHilo() {
        pausar = false;
        notify(); // Despertamos al thread
    }
}
