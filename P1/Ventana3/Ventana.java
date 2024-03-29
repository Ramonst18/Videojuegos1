package P1.Ventana3;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.*;

public class Ventana extends JFrame{
    
    public Ventana(){
        initValues();
    }

    private void initValues(){
        //Elementos de la ventana
        Fondo fondo = new Fondo("images/fondo.png");
        JButton btnStart = new JButton("Start");
        Imagen img1 = new Imagen("images/link1.png","images/link2.png", 200);
        Imagen2 img2 = new Imagen2("images/mario1.png", "images/mario2.png", fondo);
        
        //Paredes
        Wall [] bloques = new Wall[2];

        //creamos los bloques en pantalla y damos la posicion
        for (int i = 0; i < bloques.length; i++) {
            bloques[i] = new Wall("images/wall.png");
            bloques[i].setBounds( (int) (Math.random() * (800 - 100)) + 100, 110, 16, 16);
        }

        //pasamos los bloques al personaje
        img2.walls = bloques;

        //posiciones de los elementos
        fondo.setBounds(0,0,800,300);
        btnStart.setBounds(10, 10, 75, 25);
        img1.setBounds(10, 40, 42, 42);
        img2.setBounds(10, 100, 42, 42);

        //FOCUS
        btnStart.setFocusable(false);
        img2.setFocusable(true);

        //Action Listener
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //acciones al dar clic el boton
                Thread t = new Thread(img1);
                Thread t2 = new Thread(img2);

                t.start();
                t2.start();
            }
        };

        //Agregamos los actionListener
        btnStart.addActionListener(listener);
        img2.addKeyListener(img2);

        //Agregamos los elementos a la ventana
        for (int i = 0; i < bloques.length; i++) {
            add(bloques[i]);
        }
        add(btnStart);
        add(img1);
        add(img2);
        add(fondo);

        //Configuracion de la ventana
        setTitle("Ventana 3");
        setSize(300, 300);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
