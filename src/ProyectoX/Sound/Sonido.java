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
	private boolean skip = false;
	protected Player playMP3;
	protected long init;
	protected int delay;
	protected boolean stop = false;
	
	public Sonido( String file , boolean loop) {
		this.file = file;
		this.loop = loop;
		this.start();
	}
	
	public Sonido( Player mp, int delay) {
		this.delay = delay;
		skip = true;
		playMP3 = mp;
		this.start();
	}

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
	
	public void stopedd(int delay){
		new Sonido(playMP3,delay);
		loop = false;
	}

}
