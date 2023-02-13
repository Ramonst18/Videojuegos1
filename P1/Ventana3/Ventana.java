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
        JButton btnStart = new JButton("Start");
        Imagen img1 = new Imagen("images/link1.png","images/link2.png", 200);
        Imagen img2 = new Imagen("images/mario1.png", "images/mario2.png", 100);

        //posiciones de los elementos
        btnStart.setBounds(10, 10, 75, 25);
        img1.setBounds(10, 40, 42, 42);
        img2.setBounds(10, 100, 42, 42);

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

        //Agregamos los elementos a la ventana
        add(btnStart);
        add(img1);
        add(img2);

        setTitle("Ventana 3");
        setSize(300, 300);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}