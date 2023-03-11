package P1.Pong.clases;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.geom.*;

public class Pala extends JLabel implements Runnable, KeyListener{
    //atributos de la clase
    private File file;
    private String url;
    private ImageIcon icon;
    private int score = 0;

    //Constructor
    public Pala(String url){
        this.url = url;
        this.file = new File(this.url);
        this.icon = new ImageIcon(this.getClass().getResource(this.url));
        setIcon(icon);
    }// end Pala

    //Thread
    public void run(){

    }// end run

    
    //KEYLISTENER
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
    }

    
}
