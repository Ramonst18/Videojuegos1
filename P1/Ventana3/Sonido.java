package P1.Ventana3;

import java.io.File;

import javax.sound.sampled.*;

public class Sonido {
    private String ruta;
    private AudioInputStream audioStream;
    private Clip clip;
    private File file;

    public Sonido(String ruta){
        this.ruta = ruta;

        file = new File(ruta);

        //System.out.println(this.getClass().getResource(ruta));

        try {
            audioStream = AudioSystem.getAudioInputStream(this.getClass().getResource(ruta));
            clip = AudioSystem.getClip();
        } catch (Exception e) { 
            System.out.println(e);
        } // TODO: handle exception

    }

    public void play() {
        try {
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) { } // TODO: handle exception
    }
}
