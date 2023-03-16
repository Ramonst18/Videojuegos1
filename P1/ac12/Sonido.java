package P1.ac12;

import java.io.File;

import javax.sound.sampled.*;

public class Sonido {
    private String ruta;
    private AudioInputStream audioStream;
    private Clip clip;
    private File file;
    private Long microSegundos;

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

    public void playOne() {
        try {
            clip.open(audioStream);
            clip.loop(0);
        } catch (Exception e) {
        } // TODO: handle exception
    }

    public void pause(){
        microSegundos = clip.getMicrosecondPosition();
        clip.stop();
    }//end pause

    public void resume(){
        clip.close();
        try {
            audioStream = AudioSystem.getAudioInputStream(this.getClass().getResource(ruta));
            play();
            clip.setMicrosecondPosition(microSegundos);
        } catch (Exception e) { }
    }//end resume

    public void stopmusic(){
        microSegundos = 0L;
        clip.stop();
        clip.close();
    }//end stop
}
