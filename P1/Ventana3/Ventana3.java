package P1.Ventana3;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;

public class Ventana3 extends JFrame{
    public Ventana3(){
        initValues();
    }

    private void initValues(){
        //Elementos de la ventana
        Fondo3 fondo = new Fondo3("images/Fondo3.png");
        JButton btnStart = new JButton("Start");
        Imagen3 personaje = new Imagen3("images/mario1.png", "images/mario2.png", fondo);
        Base [] bases = new Base[2];
        int xBase = 0;
        //posiciones de los elementos
        personaje.setBounds(10, 10, 42, 42);
        //Inicializamos las bases y las posicionamos
        for (int i = 0; i < bases.length; i++) {
            bases[i] = new Base("images/Base.png");
            bases[i].setBounds(xBase, 55, 161, 27);

            //incrementamos las bases
            xBase += 250;
        }
        fondo.setBounds(0,0,510,72);
        btnStart.setBounds(10, 80, 75, 25);
        
        //FOCUS
        btnStart.setFocusable(false);
        personaje.setFocusable(true);

        //Pasamos la base
        personaje.bases = bases;

        //Action Listener
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Thread t = new Thread(personaje);

                t.start();
            }
        };

        //Agregamos los actionListener
        btnStart.addActionListener(listener);
        personaje.addKeyListener(personaje);

        //Agregamos los elementos a la ventana
        add(personaje);
        for (int i = 0; i < bases.length; i++) {
            add(bases[i]);
        }
        add(btnStart);
        add(fondo);

        //Configuracion de la ventana
        setTitle("Ventana 3");
        setSize(300, 150);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
