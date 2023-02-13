package P1.Ventana1;

import javax.swing.JFrame;
import java.awt.Font;

public class Ventana extends JFrame{

    //Constructor de la ventana
    public Ventana(){
        initValues();
    }//end Ventana

    //inicializamos el boton
    Button1 boton1;

    private void initValues(){
        //Metodo donde tiene que tener toda la informacion de la ventana, como objetos y variables

        //Creamos un fond para el texto
        Font font1 = new Font("Arial",1,24);

        //Agregamos los elementos a la ventana
        Label1 texto1 = new Label1("0", 500);
        Label1 texto2 = new Label1("0", 1000);

        boton1 = new Button1(texto1, texto2, "Iniciar conteo");

        //establecemos el font al texto
        texto1.setFont(font1);
        texto2.setFont(font1);

        //agregamos un actionListener al boton pasando la clase que lo implementa junto al hilo
        boton1.addActionListener(boton1);

        //Se pueden agregar metodos de tipo ActionListener a algun boton, un solo ActionListener puede ser usado para varios botones
        //Para diferenciarse se tiene que utilizar el ActionEvent

        //establecemos la posicion del elemento en la ventana y las dimenciones del elemento
        texto1.setBounds(10, 10, 50, 25);
        texto2.setBounds(10, 30, 50, 25);
        boton1.setBounds(70, 120, 150, 20);

        //agregamos el elemento a la ventana
        add(texto1);
        add(texto2);
        add(boton1);

        //Agregamos las caracteristicas a la ventana
        //Titulo de la ventana
        setTitle("Mi ventana");
        //damos el tamaño de la ventana
        setSize(300,300);
        //Que no se pueda modificar la dimension de la ventana
        setResizable(false);
        //Desabilitamos el Layout para dar las cordenadas nosotros mismos
        setLayout(null);
        //Para que aparezca en el centro de la pantalla
        setLocationRelativeTo(null);
        //Cuando se cierre se termine el programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mandamos a ser visible a la ventana
        setVisible(true);


    }//end initValues
    


}
