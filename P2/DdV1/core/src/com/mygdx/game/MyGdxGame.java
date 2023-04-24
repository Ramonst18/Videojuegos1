package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	// Las variables deben de estar en private
	private SpriteBatch batch;
	private Texture img;
	private TextureRegion region;	//sirve para tomar partes de la imagen

	@Override
	public void create() {
		// Es el metodo constructor, dentro inicializaremos todas nuestras variables u
		// objetos a utilizar
		batch = new SpriteBatch();	//Se utiliza para que las imagenes se usen de manera conjunta
		img = new Texture("mario1.png");
		region = new TextureRegion(img, 0,0,32,20);
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

		batch.end();	//FIn de los elementos
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
