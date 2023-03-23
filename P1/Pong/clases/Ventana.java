package P1.Pong.clases;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ventana extends JFrame {

    //atributows de la ventana
    private Sonido musicaFondo;
    private BtnPauseResume btnPauseResume;

    public Ventana() {
        initValues();
    }

    private void initValues() {
        // ELEMENTOS DE LA VENTANA
        Pala jugador1 = new Pala("../images/palas/palaA.png", "P1","../images/palas/palaAPotenciado.gif");
        jugador1.setDashIndicator(
                new DashIndicator("../images/iconDash/iconDashAP1.png", "../images/iconDash/iconDashAP2.png",
                        "../images/iconDash/iconDashAP3.png", "../images/iconDash/iconDashAP4.png"));
        Pala jugador2 = new Pala("../images/palas/palaB.png", "P2","../images/palas/palaBPotenciado.gif");
        jugador2.setDashIndicator(
                new DashIndicator("../images/iconDash/iconDashBP1.png", "../images/iconDash/iconDashBP2.png",
                        "../images/iconDash/iconDashBP3.png", "../images/iconDash/iconDashBP4.png"));
        Fondo fondo = new Fondo("../images/fondo.png");
        Pelota pelota = new Pelota("../images/pelota.png");
        JButton btnStart = new JButton(new ImageIcon(this.getClass().getResource("../images/ButtonStart.png")));
        JButton btnStop = new JButton(new ImageIcon(this.getClass().getResource("../images/btnStop.png")));
        btnPauseResume = new BtnPauseResume();
        JLabel demoImpulso = new JLabel(new ImageIcon(this.getClass().getResource("../images/demoImpulso.png")));
        JLabel demoGolpe = new JLabel(new ImageIcon(this.getClass().getResource("../images/demoGolpe.png")));
        JLabel demoMovimiento = new JLabel(new ImageIcon(this.getClass().getResource("../images/demoMovimiento.png")));
        
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
        demoImpulso.setBounds(10, 10, 90, 30);
        demoGolpe.setBounds(10, 45, 90, 30);
        demoMovimiento.setBounds(205, 10, 90, 80);
        
        //areglo de palas
        Pala[] palas = new Pala[2];
        palas[0] = jugador1;
        palas[1] = jugador2;
        
        //SET
        pelota.setPalas(palas);
        jugador1.setPelota(pelota);
        jugador2.setPelota(pelota);
        pelota.setBtnStop(btnStop);

        // KEYLISTENNER
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // btnStart
                if (e.getSource() == btnStart) {
                    Thread P1 = new Thread(jugador1);
                    Thread P1Indicator = new Thread(jugador1.getDashIndicator());
                    Thread P2 = new Thread(jugador2);
                    Thread P2Indicator = new Thread(jugador2.getDashIndicator());
                    Thread Tpelota = new Thread(pelota);

                    //Ocultamos las guias
                    demoImpulso.setVisible(false);
                    demoGolpe.setVisible(false);
                    demoMovimiento.setVisible(false);

                    //Damos comienzo a los jugadores
                    P1.start();
                    P1Indicator.start();
                    P2.start();
                    P2Indicator.start();
                    Tpelota.start();

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
                        jugador2.reanudarHilo();
                        pelota.reanudarHilo();
                    } else {
                        // Pausaremos
                        btnPauseResume.changeState();

                        musicaFondo.pause();

                        jugador1.pausarHilo();
                        jugador2.pausarHilo();
                        pelota.pausarHilo();
                    }
                }
                if (e.getSource() == btnStop) {
                    musicaFondo.stopmusic();

                    btnPauseResume.setPause(true);
                    btnPauseResume.changeState();

                    jugador1.resetPala();
                    jugador2.resetPala();
                    pelota.resetPosition();

                    jugador1.stopHilo();
                    jugador2.stopHilo();
                    pelota.stopHilo();

                    btnStart.setEnabled(true);

                    //MOstramos las guias
                    demoImpulso.setVisible(true);
                    demoGolpe.setVisible(true);
                    demoMovimiento.setVisible(true);
                }
            }
        };

        // asignamos las acciones a los botones
        btnStart.addActionListener(listener);
        btnPauseResume.addActionListener(listener);
        btnStop.addActionListener(listener);
        jugador1.addKeyListener(jugador1);
        jugador2.addKeyListener(jugador2);

        // FOCUS
        jugador1.setFocusable(true);
        jugador2.setFocusable(false);
        btnStart.setFocusable(false);
        btnPauseResume.setFocusable(false);
        btnStop.setFocusable(false);

        // agregamos los elementos a la ventana
        add(demoImpulso);
        add(demoGolpe);
        add(demoMovimiento);
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
