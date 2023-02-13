package P1.Ventana3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Imagen extends JLabel implements Runnable{
    
    //Atributos
    ImageIcon icon;
    private String url, url2;
    private int x, y, tiempo;
    boolean derecha = true;

    public Imagen(String url, String url2, int tiempo){
        //damos la ruta de donde esta nuestra imagen/icono, el getClass con getResource nos da la ubicacion de nuestra clase
        icon = new ImageIcon(this.getClass().getResource(url));
        
        this.url = url;
        this.url2 = url2;
        this.tiempo = tiempo;

        //agregamos el icon al label indicando su icon
        setIcon(icon);

    }

    public void run(){
        //obtenemos las posiciones actuales de los elementos
        this.x = this.getX();
        this.y = this.getY();

        //ciclo
        while(true){

            //preguntamos si es par o inpar, para cambiar los iconos
            if( this.x % 2 != 0){
                icon = new ImageIcon(this.getClass().getResource(this.url2));
            }else{
                icon = new ImageIcon(this.getClass().getResource(url));
            }

            //cambiamos el icono
            setIcon(icon);

            //realizamos el movimiento
            setBounds(this.x, this.y, 42, 42);

            //tratamos de hacer las pausas (interfambio de imagenes, frame)
            try {
                Thread.sleep(this.tiempo);
            } catch (Exception e) {
                // TODO: handle exception
            }

            //incrementamos la posicion
            if (x > 250) {
                derecha = false;
            } 
            if (x < 0) {
                derecha = true;
            }
            //incrementamos o decrementamos la posicion
            if (derecha) {
                x += 3;
            } else {
                x -= 3;
            }
        }// end while
    }
}
