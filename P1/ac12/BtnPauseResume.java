package P1.ac12;

import javax.swing.JButton;

public class BtnPauseResume extends JButton{

    //atributos de clase
    private final String txtPause = "Pause", txtResume ="Resume";
    private boolean pause = false;

    //constructor
    public BtnPauseResume(){
        setText(txtPause);
    }

    public void changeText(){

        //Preguntamos si esta en pause para actualizar el texto
        if (this.pause) {
            setText(this.txtResume);
        }else{
            setText(this.txtPause);
        }
    }

    //getters and setters
    public boolean getPause(){
        return pause;
    }

    public void setPause(boolean pause){
        this.pause = pause;
    }

}
