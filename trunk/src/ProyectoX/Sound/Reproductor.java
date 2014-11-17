package ProyectoX.Sound;

import java.util.ArrayList;
 

public class Reproductor{
	private boolean enabled = true;
	
	private Sonido sonido;
	private int current = 0;
	
	public void addSound(String path, boolean loop){
		if(enabled){
			Sonido sound = new Sonido(path, loop);
			if(loop)
				sonido = sound;
		}
	}
	
	public void stop(int delay){
		if(sonido!=null){
			sonido.stopedd(delay);
		}
	}
	
	public void setEnabled(boolean arg){
		enabled = arg;
		if(!arg){
			stop(0);
		}
		else{
			sonido = new Sonido(sonido.getPath(),true);
		}
	}
	
}
