package ProyectoX.Sound;

import java.util.ArrayList;
 

public class Reproductor extends Thread{

	 private boolean stop = false;
	
	private ArrayList sonidos;
	
	public Reproductor(){
		sonidos = new ArrayList();
		this.start();
	}
	
	public void addSound(Sonido sound){
		if(sound!=null)
		sonidos.add(sound);
		//sound.play();
	}
	
	public void verificarStop(){
		/*for(int i = 0; i< sonidos.size(); i++){
			Sonido sn = (Sonido) sonidos.get(i);
			if(sn.getStop()){
				
			}
		}
		*/
	}
	
    public void run(){
    	while(!stop){
    		for(int i = 0; i< sonidos.size(); i++){
	    		Sonido sn = (Sonido) sonidos.get(i);
	    		
	    		if(!sn.isReproduciendo()){
	    			//sn.start();    
	    		}
	    		else{
		    		if(sn.getPlayer().isActive()){
		    			if (sn.isBucle())
		    				sn.setReproduciendo(false);
		    			else{
		    				sonidos.remove(i);
		    			}
		    		}
	    		}
    		}
    	}
    	
    }

}
