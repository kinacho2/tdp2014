package ProyectoX.Sound;

import java.util.ArrayList;
 

public class Reproductor{
	
	private Sonido sonido;
	
	public Reproductor(){
		
	}
	
	public void addSound(Sonido sound){
		if(sound.getLoop())
			sonido = sound;
	}
	
	public void stop(int delay){
		if(sonido!=null){
			sonido.stopedd(delay);
		}
	}
	
	public void setSilence(boolean silence){
		
	}
	
}
