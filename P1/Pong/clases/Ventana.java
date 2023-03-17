package P1.Pong.clases;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JFrame;

public class Ventana extends JFrame{
    
    public Ventana(){
        initValues();
    }

    private void initValues(){
        //ELEMENTOS DE LA VENTANA
        Pala jugador1 = new Pala("../images/pala.png");
        Fondo fondo = new Fondo("../images/fondo.png");
        JButton btnStart = new JButton(new ImageIcon(this.getClass().getResource("../images/ButtonStart.png")));
        BtnPauseResume btnPauseResume = new BtnPauseResume();

        //Posicionamos los elementos
        jugador1.setBounds(10, 100, 12, 42);
        fondo.setBounds(0, 0, 300, 300);
        btnStart.setBounds(5, 300, 75, 25);
        btnPauseResume.setBounds(85, 300, 90, 25);
        
        //KEYLISTENNER
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e){

                //btnStart
                if (e.getSource() == btnStart) {
                    Thread t = new Thread(jugador1);

                    t.start();

                    btnStart.setEnabled(false);
                }
                if (e.getSource() == btnPauseResume) {
                    //preguntamos si esta en pausa
                    if (btnPauseResume.getPause()) {
                        //Reanudamos
                        btnPauseResume.changeState();

                        jugador1.reanudarHilo();
                    }else{
                        //Pausaremos
                        btnPauseResume.changeState();

                        jugador1.pausarHilo();
                    }

                }
            }
        };

        //asignamos las acciones a los botones
        btnStart.addActionListener(listener);
        btnPauseResume.addActionListener(listener);
        jugador1.addKeyListener(jugador1);

        //FOCUS
        jugador1.setFocusable(true);
        btnStart.setFocusable(false);
        btnPauseResume.setFocusable(false);

        //agregamos los elementos a la ventana
        add(jugador1);
        add(btnStart);
        add(btnPauseResume);
        add(fondo);

        //Configuracion de la ventana
        setTitle("Pong");
        setSize(300, 365);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
