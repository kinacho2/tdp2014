package ProyectoX.Sound;

import java.util.ArrayList;
 

public class Reproductor{

	 private boolean stop = false;
	
	private ArrayList sonidos;

	private int cant = 0;
	
	public Reproductor(){
		sonidos = new ArrayList();
	
	}
	
	public void addSound(String sound){
		sonidos.add(sound);
    	Sonido sn = new Sonido(sound);
	}
	
	public void verificarStop(){
		/*for(int i = 0; i< sonidos.size(); i++){
			Sonido sn = (Sonido) sonidos.get(i);
			if(sn.getStop()){
				
			}
		}
		*/
	}
	

}
