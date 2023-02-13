package P1.Ventana1;

import javax.swing.JButton;
import java.awt.event.*;

public class Button1 extends JButton implements ActionListener{

    //variables que resibiremos
    private Label1 label1;
    private Label1 label2;

    //constructor
    public Button1(Label1 label1, Label1 label2, String text){
        this.label1 = label1;
        this.label2 = label2;
        setText(text);
    }

    //accion que se realizara cuando se pulse el boton
    public void actionPerformed(ActionEvent e){
        //desabilitamos el hilo
        this.setEnabled(false);

        //Damos el boton en cuestion del cual tarde mas en terminar
        if(label1.getTime() > label2.getTime()){
            label1.setBoton(this);
        }else{
            label2.setBoton(this);
        }

        //creamos los hilos para cada uno de los label
        Thread tr = new Thread(label1);
        Thread tr2 = new Thread(label2);
        tr.start();
        tr2.start();

    }

}
