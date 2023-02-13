package P1.Ventana2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame {
    public Ventana(){
        initValues();
    }

    private void initValues(){
        //Elementos de pantalla
        Font font1 = new Font("Arial",1,22);

        //Componentes de la pantalla
        JLabel texto1 = new JLabel("0");
        JButton btnStart = new JButton("Start");
        LabelKey lKey = new LabelKey("0");

        texto1.setFont(font1);
        lKey.setFont(font1);

        //damos y quitamos el focus
        lKey.setFocusable(true);
        btnStart.setFocusable(false);

        //Dimensiones de los componentes
        texto1.setBounds(10, 20, 50, 25);
        btnStart.setBounds(10, 70, 80, 25);
        lKey.setBounds(10, 40, 50, 25);

        //ActionListener
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //acciones al dar clic el boton
                Thread tKey = new Thread(lKey);
                MyThread t = new MyThread(texto1, btnStart);
                
                //ejecutamos los hilos
                t.start();
                tKey.start();
            }
        };

        //agregamos acciones a los componentes
        btnStart.addActionListener(listener);

        //Agregamos los coponentes a la ventana
        add(texto1);
        add(btnStart);
        add(lKey);

        //configuracion basica
        setTitle("Ventana #2");
        setSize(300, 300);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
