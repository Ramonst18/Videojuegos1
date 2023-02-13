package P1.Ventana1;

import javax.swing.JLabel;

public class Label1 extends JLabel implements Runnable{
    
    private int time;
    private Button1 boton;

    public Label1(String text, int time){
        setText(text);
        this.time = time;
    }

    public void run(){
        //realizamos un conteo con el for con el cual actualizaremos el texto
        for (int i = 0; i <= 10; i++) {
            //dormimos el hilo durante un tiempo establecido
            try {
                Thread.sleep(time);
            } catch (Exception e) {
                // TODO: handle exception
            }

            //actualizamos el texto del label
            setText(String.valueOf(i));
        }

        //si el boton fue asignado, este se activara una vez se acabe el proceso
        if(boton != null){
            activarBoton();
        }
    }

    public int getTime(){
        return time;
    }

    public void setBoton(Button1 boton){
        this.boton = boton;
    }

    public void activarBoton(){
        boton.setEnabled(true);
    }
}
