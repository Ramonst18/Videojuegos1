package P1.Pong.clases;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BtnPauseResume extends JButton{

    //atributos de clase
    private boolean pause = false;
    private ImageIcon imaResume, imaPause;

    //constructor
    public BtnPauseResume(){
        //Creamos las classes de los iconos
        imaResume = new ImageIcon(this.getClass().getResource("../images/btnResume.png"));
        imaPause = new ImageIcon(this.getClass().getResource("../images/btnPause.png"));

        //actualizamos el icon
        setIcon(imaPause);
    }

    public void changeState(){
        //preguntamos si esta en pause para realizar una accion
        if (this.pause) {
            //Continuar
            setIcon(imaPause);
            this.pause = false;
        }else{
            //Pausar
            setIcon(imaResume);
            this.pause = true;
        }

        System.out.println(this.getPause());
    }

    //getters and setters
    public boolean getPause(){
        return pause;
    }

}
