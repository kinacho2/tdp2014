package ProyectoX.Frames;

import java.net.URL;
import ProyectoX.Aplication;
import ProyectoX.Paneles.AbstractPanel;

/**
 * Clase Thread que crea una SplashScreen que aparece en el centro de la pantalla
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class MenteSplash extends Thread{

	private SplashScreen splash;
	private Aplication api;
	private int select;
	private AbstractPanel panel;
	
	/**
	 * Constructor de la clase MenteSplash
	 * @param duracion duracion del SplashScreen
	 * @param url imagen que mostrara el SplashScreen
	 * @param api la aplicacion que ejecutara el siguiente metodo al terminarse el SplashScreen
	 * @param select indica la nave que selecciono el jugador
	 */
	public MenteSplash(int duracion, URL url, Aplication api, int select){
		splash = new SplashScreen(duracion, url);
		this.api = api;
		this.select = select;
		panel = null;
	}
	
	/**
	 * Constructor de la clase MenteSplash
	 * @param duracion duracion del SplashScreen
	 * @param url imagen que mostrara el SplashScreen
	 * @param panel AbstractPanel que ejecutara la siguiente funcion al finalizar el SplashScreen
	 */
	
	public MenteSplash(int duracion, URL url, AbstractPanel panel){
		splash = new SplashScreen(duracion, url);
		this.panel = panel;
	}
	
	/**
	 * redefine run() de la clase Thread
	 * ejecuta el SplashScreen y luego que este finalize ejecuta la siguiente funcion
	 */
	
	public void run(){
		splash.showSplash();
		if(panel != null)
			panel.function();
		else
			api.initGame(select);
	}

}
