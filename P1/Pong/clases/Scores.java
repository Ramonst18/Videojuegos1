package P1.Pong.clases;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Scores extends JLabel{
    //Atributos de clase
    private Font font = new Font("Arial", Font.BOLD, 40);

    //Constructor
    public Scores(){
        setFont(font);
        this.setForeground(Color.WHITE);
        setText("0");
    }

    public void actualizarPuntaje(int score){
        /*Metodo que actualizara el puntaje del personaje */
        setText(String.valueOf(score));
    }

}
