package ProyectoX.Sound;

import java.util.ArrayList;
 
/**
 * Clase que crea sonidos
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public class Reproductor{
	private static boolean enabled = true;
	
	private String path;
	private Sonido sonido;
	
	/**
	 * si esta habilitado crea y ejecuta un nuevo sonido
	 * si se indica un loop se guarda la instancia de sonido como atributo
	 * antes de crear un nuevo sonido con loop=true se recomienda hacer stop()
	 * @param path ubicacion relativa del archivo de sonido
	 * @param loop indica si el sonido debe repetirse
	 */
	
	public void addSound(String path, boolean loop){
		
		if(enabled){
			Sonido sound = new Sonido(path, loop);
			if(loop)
				sonido = sound;
		}
		if(loop)
			this.path = path;
	}
	
	/**
	 * le ordena al sonido que se detenga
	 * @param delay el tiempo que tiene que seguir ejecutandose el sonido
	 */
	
	public void stop(int delay){
		if(sonido!=null){
			sonido.stopedd(delay);
		}
	}
	
	/**
	 * inicializa o pausa el sonido que contiene la clase
	 * @param arg booleano, si es true se efectua una pausa, caso contrario se inicializa nuevamente
	 */
	
	public void setEnabled(boolean arg){
		enabled = arg;
		if(!arg){
			stop(0);
		}
		else{
			sonido = new Sonido(path,true);
		}
	}
	
	/**
	 * retorna el estado actual de la clase Reproductor
	 * @return true si esta habilitado o false en caso contrario
	 */
	
	public boolean getEnabled(){
		return enabled;
	}
	
}
