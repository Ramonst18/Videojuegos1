package P1.Ventana2;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MyThread extends Thread{
    
    private JLabel texto1;
    private JButton boton1;
    private boolean derecha = true;
    private int x;
    private int y;

    public MyThread(JLabel texto1, JButton boton){
        this.texto1 = texto1;
        this.boton1 = boton;
        x = (int) this.texto1.getX();
        y = (int) this.texto1.getY();
    }

    public void run(){
        boton1.setEnabled(false);

        //Cambiamos los valores del texto
        while (true) {
            try {
                Thread.sleep(500);
                
                //realizamos el movimiento del texto
                texto1.setLocation(x, y);

                //comparamos la posicion x
                if ( x == 270 ) {
                    derecha = false;
                } else if(x == 0){
                    derecha = true;
                }

                //cambiamos la direccion en la que se mueve
                if (derecha) {
                    //Incrementamos las variables
                    x += 10;   
                }else{
                    x -= 10;
                }
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

}
