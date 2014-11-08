package ProyectoX.Sound;

import java.util.ArrayList;

public class Reproductor {

	private ArrayList sonidos;
	
	public Reproductor(){
		sonidos = new ArrayList();
	}
	
	public void addSound(Sonido sound){
		sonidos.add(sound);
		//sound.start();
	}
	
	public void verificarStop(){
		for(int i = 0; i< sonidos.size(); i++){
			Sonido sn = (Sonido) sonidos.get(i);
			if(sn.getStop()){
				sonidos.remove(i);
			}
		}
	}
}
