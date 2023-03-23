package P1.Pong.clases;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Scores extends JLabel{
    //Atributos de clase
    private Font font = new Font("Arial", Font.BOLD, 40);
    private int score;

    //Constructor
    public Scores(){
        setFont(font);
        this.setForeground(Color.WHITE);
        score = 0;
        setText(String.valueOf(score));
    }

    public void actualizarPuntaje(int score){
        /*Metodo que actualizara el puntaje del personaje */
        setText(String.valueOf(score));
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
    
}
