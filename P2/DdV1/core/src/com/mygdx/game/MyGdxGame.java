package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MyGdxGame extends ApplicationAdapter {
	// Las variables deben de estar en private
	private SpriteBatch batch;
	private Texture img;
	private TextureRegion region;	//sirve para tomar partes de la imagen
	private Sprite miSprite;
	private float posX = 300, width, height, velocidad = 100, tiempoT = 0;
	private boolean derechaIzquierda; //true derecha, false izquierda

	@Override
	public void create() {
		// Es el metodo constructor, dentro inicializaremos todas nuestras variables u
		// objetos a utilizar
		batch = new SpriteBatch();	//Se utiliza para que las imagenes se usen de manera conjunta
		img = new Texture("mario1.png");
		region = new TextureRegion(img, 0,0,32,20);
		miSprite = new Sprite(img,0,0,42,42);	//Recortamos directamente la imagen
		miSprite.setPosition(300, 350);

		width = Gdx.graphics.getWidth();	//Obtenemos el ancho
		height = Gdx.graphics.getWidth();	//obtenemos el alto

		derechaIzquierda = true;

		miSprite.rotate(90);//giramos el sprite
	}

	@Override
	public void render() {
		// Metodo que se encargara de cargar y actualizar los elementos
		// Este es como el while principal del juego
		//Aqui pondremos las teclas, sonidos, etc...
		ScreenUtils.clear(1, 1, 1, 1);	//Codigo para cambiar el color del background
		batch.begin();	//Comienzo de los elementoss
		//Entre medio del begin y el end se pondra el codigo de cada uno de los elementos

		//Dibujamos en pantalla
		batch.draw(img, 10, 50, 100,75);	//posicionamos un elemento en pantalla
		batch.draw(region,10,170,50,50);
		miSprite.draw(batch);
		/*
		//preguntamos la colision con la ventana
		if (posX == (width - 43)) {
			derechaIzquierda = false;
		}else if (posX == 0) {
			derechaIzquierda = true;
		}

		//posicion a moverse
		if (derechaIzquierda) {
			posX++;
		}else{
			posX--;
		}
		 */
		miSprite.setPosition(posX, 350);//cambiamos la posicion
		miSprite.rotate(90);

		batch.end();	//FIn de los elementos

		//ESCUCHA DEL TECLADO
		boolean derecha = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean izquierda = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);

		float tiempo = Gdx.graphics.getDeltaTime();	//Nos da el tiempo en que pasa por refresco de imagen

		//MOVIMIENTO DEL SPRITE
		if (izquierda) {

			//COmprobamos si ya pasaron 1 segundos
			if (tiempoT>=1) {
				velocidad = 300;
			}else{
				tiempoT += tiempo;
				velocidad = 100;
			}

			//Decrementamos la posicion en x
			posX-= velocidad * tiempo;
		}else if (derecha) {
			//COmprobamos si ya pasaron 1 segundos
			if (tiempoT>=1) {
				velocidad = 300;
			}else{
				tiempoT += tiempo;
				velocidad = 100;
			}

			//incrementamos la poscion en x
			posX+= velocidad * tiempo;
		}else{
			tiempoT = 0;
		}

		System.out.println(tiempoT);

		miSprite.setPosition(posX, 350);

		//TAREA INCREMENTAR LA VELOCIDAD DEL MOVIMIENTO DEL MONO DESPUES DE UN TIEMPO Y CUANDO LLEGUE A UNA VELOCIDAD
		//ESTABLECIDA, NO INCREMENTAR LA VELOCIDAD
	}

	@Override
	public void dispose() {
		//Metodo que se encarga de cerrar los elementos esto para liberar memoria
		//Es urgente de cerrar los elementos si no los elementos se quedaran guardados en memoria
		//de la grafica y de la Ram
		batch.dispose();
		img.dispose();
	}
}
