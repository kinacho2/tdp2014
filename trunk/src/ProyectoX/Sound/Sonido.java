package ProyectoX.Sound;

import java.io.InputStream;
import ProyectoX.lib.javazoom.jl.player.Player;

/**
 * Clase que crea una Player que reproduce formato MP3 en un hilo aparte, para 
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Sonido extends Thread {
	
	protected String file;
	protected boolean loop;
	protected boolean comenzar = false;
	private boolean skip = false;
	protected Player playMP3;
	protected long init;
	protected int delay;
	protected boolean stop = false;
	
	/**
	 * Constructor de la clase Sonido
	 * setea los atributos e inicializa el hilo
	 * @param file ubicacion relativa del archivo de sonido
	 * @param loop indica si el sonido debe repetirse
	 */
	public Sonido( String file , boolean loop) {
		this.file = file;
		this.loop = loop;
		this.start();
	}
	
	/**
	 * Constructor de la clase Sonido
	 * @param mp de tipo Player
	 * @param delay tiempo que se reproducira el Sonido
	 */
	
	public Sonido( Player mp, int delay) {
		this.delay = delay;
		skip = true;
		playMP3 = mp;
		this.start();
	}
	
	/**
	 * redefine run() de la clase Thread
	 * crea un sonido a partir del path relativo pasado en el constructor
	 * si el loop esta en true el sonido vuelve a comenzar
	 */

	public void run() {
			if(!skip)
				do{
					InputStream fis = this.getClass().getResourceAsStream(file);
					crearPlayer(fis);
				}
				while (loop);
			else{
				
				if(!comenzar){
					init = System.currentTimeMillis();
					comenzar = true;
				}
				while(!stop){
					if(System.currentTimeMillis() - init >= delay){
						playMP3.close();
						stop = true;
					}
				}
			}
	}
	
	/**
	 * crea una instancia de Player
	 * @param fis InputStream del archivo de Sonido
	 */
	
	private void crearPlayer(InputStream fis){
		try{
			playMP3 = new Player(fis);
			playMP3.play();
		}
		catch(Exception ex)
		{  
			ex.printStackTrace();
		}
	}
	
	/**
	 * @return true si hay loop
	 */
	
	public boolean getLoop(){
		return loop;
	}
	
	/**
	 * crea un nuevo sonido con el Player actual y le indica el tiempo que debe detenerse
	 * y setea el loop en false para que no se repita
	 * @param delay
	 */
	
	public void stopedd(int delay){
		new Sonido(playMP3,delay);
		loop = false;
	}
	
	/**
	 * @return path relativo del archivo de sonido
	 */

	public String getPath(){
		return file;
	}
}
