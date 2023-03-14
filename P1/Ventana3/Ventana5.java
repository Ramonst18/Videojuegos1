package P1.Ventana3;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;

public class Ventana5 extends JFrame{
    
    //elementos
    Sonido sonido;

    public Ventana5(){
        initValues();
    }

    private void initValues(){
        //Elementos de la ventana
        JButton btnStart = new JButton("Start");
        JButton btnPause = new JButton("Pause");
        JButton btnStop = new JButton("Stop");
        JButton btnResume = new JButton("Resume");

        //Posicionamos los elementos
        btnStart.setBounds(10, 20, 75, 25);
        btnPause.setBounds(95, 20, 75, 25);
        btnStop.setBounds(180, 20, 75, 25);
        btnResume.setBounds(265, 20, 85, 25);

        //acciones de los botones
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == btnStart) {
					sonido = new Sonido("music/mario1v2.wav");
					sonido.play();
				}
            }
        };

        btnStart.addActionListener(listener);

        //agregamos los elementos a la ventana
        add(btnStart);
        add(btnPause);
        add(btnStop);
        add(btnResume);

        //Configuracion de la ventana
        setTitle("Ventana 5");
        setSize(400, 100);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
