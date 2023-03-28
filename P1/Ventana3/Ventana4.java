package P1.Ventana3;


import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.*;

public class Ventana4 extends JFrame{
    boolean bandera = true;

    public Ventana4(){
        initValues();
    }

    private void initValues(){
        //Elementos de la ventana
        Monito monito = new Monito("images/mario1.png", "images/mario2.png");
        JButton btnStart = new JButton("Start");
        Reloj reloj = new Reloj();
        //Circulo c1 = new Circulo();
        //c1.setBackground(Color.GREEN);
        //monito.c1 = c1;
        //JButton btnPause = new JButton("Pause");
        //JButton btnReanudar = new JButton("Reanudar");
        //JButton btnStop = new JButton("Stop");

        //posiciones de los elementos
        monito.setBounds(10, 10, 42, 42);
        btnStart.setBounds(10, 60, 75, 25);
        reloj.setBounds(10, 90, 90, 90);
        //c1.setBounds(10, 90, 70, 150);
        //btnPause.setBounds(90, 60, 75, 25);
        //btnReanudar.setBounds(170, 60, 75, 25);
        //btnStop.setBounds(250, 60, 75, 25);

        //FOCUS
        btnStart.setFocusable(false);
        monito.setFocusable(true);

        //ActionLisstener
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent ae){

                if (ae.getSource() == btnStart) {
                    Thread t = new Thread(monito);
                    t.start();
                    Thread tReloj = new Thread(reloj);
                    tReloj.start();
                }
                /*
                if (ae.getSource() == btnPause) {

                    if (bandera) {
                        monito.pausarHilo();
                        bandera = false;
                    } else{
                        monito.reanudarHilo();
                        bandera = true;
                    }
                }
                
                if (ae.getSource() == btnReanudar) {
                    monito.reanudarHilo();
                } 
                if (ae.getSource() == btnStop) {
                    monito.stopHilo();
                }*/

            }
        };

        //Agregamos los action LIstener
        btnStart.addActionListener(listener);
        monito.addKeyListener(monito);
        //btnPause.addActionListener(listener);
        //btnReanudar.addActionListener(listener);
        //btnStop.addActionListener(listener);

        //agregamos los elementos a la ventana
        
        add(monito);
        add(reloj);
        //add(c1);
        add(btnStart);
        //add(btnPause);
        //add(btnReanudar);
        //add(btnStop);

        //Configuracion de la ventana
        setTitle("Ventana 4");
        setSize(300, 300);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
