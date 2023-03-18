package P1.Pong.clases;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JFrame;

public class Ventana extends JFrame {

    private Sonido musicaFondo;

    public Ventana() {
        initValues();
    }

    private void initValues() {
        // ELEMENTOS DE LA VENTANA
        Pala jugador1 = new Pala("../images/palaA.png");
        jugador1.setDashIndicator(
                new DashIndicator("../images/iconDash/iconDashAP1.png", "../images/iconDash/iconDashAP2.png",
                        "../images/iconDash/iconDashAP3.png", "../images/iconDash/iconDashAP4.png"));
        Pala jugador2 = new Pala("../images/palaB.png");
        jugador2.setDashIndicator(
                new DashIndicator("../images/iconDash/iconDashBP1.png", "../images/iconDash/iconDashBP2.png",
                        "../images/iconDash/iconDashBP3.png", "../images/iconDash/iconDashBP4.png"));
        Pelota pelota = new Pelota("../images/pelota.png");
        Fondo fondo = new Fondo("../images/fondo.png");
        JButton btnStart = new JButton(new ImageIcon(this.getClass().getResource("../images/ButtonStart.png")));
        JButton btnStop = new JButton(new ImageIcon(this.getClass().getResource("../images/btnStop.png")));
        BtnPauseResume btnPauseResume = new BtnPauseResume();

        // Posicionamos los elementos
        jugador1.setBounds(10, 130, 12, 42);
        jugador2.setBounds(263, 130, 12, 42);
        pelota.setBounds(144, 150, 8, 8);
        fondo.setBounds(0, 0, 300, 300);
        btnStart.setBounds(30, 300, 75, 25);
        btnPauseResume.setBounds(110, 300, 90, 25);
        btnStop.setBounds(205, 300, 50, 25);
        jugador1.getScores().setBounds(110, 6, 30, 30);
        jugador2.getScores().setBounds(165, 6, 30, 30);
        jugador1.getDashIndicator().setBounds(5, 303, 20, 20);
        jugador2.getDashIndicator().setBounds(260, 303, 20, 20);

        // KEYLISTENNER
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // btnStart
                if (e.getSource() == btnStart) {
                    Thread t = new Thread(jugador1);
                    Thread indicator = new Thread(jugador1.getDashIndicator());

                    t.start();
                    indicator.start();

                    btnStart.setEnabled(false);

                    musicaFondo = new Sonido("../music/musicPong.wav");
                    musicaFondo.play();
                }
                if (e.getSource() == btnPauseResume) {
                    // preguntamos si esta en pausa
                    if (btnPauseResume.getPause()) {
                        // Reanudamos
                        btnPauseResume.changeState();

                        musicaFondo.resume();

                        jugador1.reanudarHilo();
                    } else {
                        // Pausaremos
                        btnPauseResume.changeState();

                        musicaFondo.pause();

                        jugador1.pausarHilo();
                    }
                }
                if (e.getSource() == btnStop) {
                    musicaFondo.stopmusic();

                    btnPauseResume.setPause(true);
                    btnPauseResume.changeState();

                    jugador1.resetPala();
                    jugador2.resetPala();

                    jugador1.stopHilo();
                    //jugador2.stopHilo();

                    btnStart.setEnabled(true);

                }
            }
        };

        // asignamos las acciones a los botones
        btnStart.addActionListener(listener);
        btnPauseResume.addActionListener(listener);
        btnStop.addActionListener(listener);
        jugador1.addKeyListener(jugador1);

        // FOCUS
        jugador1.setFocusable(true);
        btnStart.setFocusable(false);
        btnPauseResume.setFocusable(false);
        btnStop.setFocusable(false);

        // agregamos los elementos a la ventana
        add(jugador1);
        add(jugador1.getScores());
        add(jugador1.getDashIndicator());
        add(jugador2);
        add(jugador2.getScores());
        add(jugador2.getDashIndicator());
        add(btnStart);
        add(btnPauseResume);
        add(btnStop);
        add(pelota);
        add(fondo);

        // Configuracion de la ventana
        setTitle("Pong");
        setSize(300, 365);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
