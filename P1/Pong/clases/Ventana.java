package P1.Pong.clases;

import javax.swing.JFrame;

public class Ventana extends JFrame{
    
    public Ventana(){
        initValues();
    }

    private void initValues(){
        //iniciamos las clases
        Pala jugador1 = new Pala("../images/pala.png");
        Fondo fondo = new Fondo("../images/fondo.png");

        //Posicionamos los elementos
        jugador1.setBounds(10, 100, 12, 42);
        fondo.setBounds(0, 0, 300, 300);

        //agregamos los elementos a la ventana
        add(jugador1);
        add(fondo);

        //Configuracion de la ventana
        setTitle("Pong");
        setSize(300, 300);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
