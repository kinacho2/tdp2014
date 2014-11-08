package ProyectoX.Sound;

import java.io.File;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sonido extends Thread{
	
	
    private Player player;
    private boolean bucle;
    private boolean stop = false;
    private boolean reproduciendo = false;
    private String sl; 

    public Sonido(String sl, boolean bucle) {
    		this.sl = sl;
		this.start();
		this.bucle = bucle;		
    }
    
    public void play() {
        try {
        	InputStream arch = this.getClass().getResourceAsStream(sl);
			player = new Player(arch);
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
    }

    public void Stop() throws Exception {
    	stop = true;
    }
    
    public void run(){
    	while(!stop){
    		if(!reproduciendo){
    			play();    
    			reproduciendo = true;
    		}
    		
    		if(player.isComplete()){
    			if (bucle)
    				reproduciendo = false;
    			else
    				stop = true;
    		}
    		
    	}
    	
    }
    
    public boolean getStop(){
    	return stop;
    }
}