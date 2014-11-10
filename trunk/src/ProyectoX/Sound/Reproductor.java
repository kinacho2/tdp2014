package ProyectoX.Sound;

import java.util.ArrayList;
 

public class Reproductor{

	private boolean stop = false;
	
	private Sonido sonido;

	private int cant = 0;
	
	public Reproductor(){
		
	}
	
	public void addSound(Sonido sound){
		if(sound.getLoop())
			sonido = sound;
	}
	
	public void stop(){
		if(sonido!=null){
			sonido.stopedd();
			//sonido.destroy();
		}
	}
	
}
