package ProyectoX.Sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sonido extends Thread {
	
	protected String file;
	protected boolean loop;
	protected boolean comenzar = false;
	private boolean skip = true;
	protected Player playMP3;
	protected boolean contador = false;
	protected long init;
	protected int delay;
	
	public Sonido( String file , boolean loop) {
		this.file = file;
		this.loop = loop;
		delay = 1000;
		this.start();
	}

	@Override
	public void run() {
		
			
			
			do{
				
				if(!comenzar){
					InputStream fis = this.getClass().getResourceAsStream(file);
					crearPlayer(fis);
					comenzar = true;
				}
				
				
				if(playMP3.isComplete()){
					comenzar = false;
				}
				
				
				
			}
			while (loop);
		
	}
	
	private void crearPlayer(InputStream fis ){
		try{
			playMP3 = new Player(fis);
			playMP3.play();
		}
		catch(Exception ex)
		{  
			ex.printStackTrace();
		}
	}
	
	public boolean getLoop(){
		return loop;
	}
	
	public void stopedd(){
		loop = false;
		playMP3.close();
	}

}
