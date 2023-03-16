package P1.ac12;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.*;

public class Ventana extends JFrame {

    private Sonido sonido;

    public Ventana() {
        initValues();
    }

    private void initValues() {
        // Elementos de la ventana
        Fondo fondo = new Fondo("images/Fondo3.png");
        Personaje personaje = new Personaje("images/link1.png", "images/link2.png", fondo);
        JButton btnStart = new JButton("Start");
        BtnPauseResume btnPause = new BtnPauseResume(); // Pause and Resume
        Base bases[] = new Base[1];
        JButton btnStop = new JButton("Stop");

        // posicionamos los elementos en la ventana
        fondo.setBounds(0, 0, 510, 72);
        personaje.setBounds(10, 15, 42, 42);
        btnStart.setBounds(10, 80, 75, 25);
        btnPause.setBounds(95, 80, 100, 25);
        btnStop.setBounds(200, 80, 75, 25);
        for (int i = 0; i < 1; i++) {
            bases[i] = new Base("images/Base.png");
            bases[i].setBounds(15, 60, 161, 27);
        }

        personaje.bases = bases;

        // action listener
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == btnStart) {
                    Thread t = new Thread(personaje);

                    t.start();
                    sonido = new Sonido("music/laTormenta.wav");
                    sonido.play();
                    btnStart.setEnabled(false);
                }
                if (ae.getSource() == btnStop) {
                    sonido.stopmusic();

                    btnStart.setEnabled(true);
                    btnPause.setPause(false);

                    btnPause.changeText();

                    personaje.resetPosition();
                    personaje.stopHilo();
                }
                if (ae.getSource() == btnPause) {
                    // si esta en pause
                    if (btnPause.getPause()) {
                        // quitamos el pause y reanudamos todo
                        btnPause.setPause(false);
                        btnPause.changeText();

                        sonido.resume();

                        personaje.reanudarHilo();

                    } else {
                        // pondemos el pause a todos los elementos
                        btnPause.setPause(true);
                        btnPause.changeText();

                        sonido.pause();

                        personaje.pausarHilo();

                    }
                }
            }
        };

        // damos los listener
        btnStart.addActionListener(listener);
        btnPause.addActionListener(listener);
        personaje.addKeyListener(personaje);
        btnStop.addActionListener(listener);

        // FOCUS
        personaje.setFocusable(true);
        btnStart.setFocusable(false);
        btnPause.setFocusable(false);
        btnStop.setFocusable(false);

        // agregamos los elementos a la ventana
        add(personaje);
        add(btnStart);
        add(btnStop);
        add(btnPause);
        for (int i = 0; i < 1; i++) {
            add(bases[i]);
        }
        add(fondo);

        // Configuracion de la ventana
        setTitle("ac12");
        setSize(300, 150);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
