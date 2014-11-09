package ProyectoX.Sound;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import javax.swing.*;


public class Sonido{
	
	
    private Clip player;
    private boolean bucle;
   

	private boolean stop = false;
    private boolean reproduciendo = false;
    private String sound; 

    public Sonido(String sl, boolean bucle) {
    	 
    		 this.sound = "ProyectoX/sounds/music/Enclave.mp3";
    			
    		 this.bucle = bucle;	
    		 //InputStream arch = this.getClass().getResourceAsStream(sound);

    	      try {
    	         // Open an audio input stream.
    	    	 InputStream arch = this.getClass().getResourceAsStream(sound);
 
    	         //AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(sound));
    	         // Get a sound clip resource.
    	         player = AudioSystem.getClip();
    	         // Open audio clip and load samples from the audio input stream.
    	         player.open(AudioSystem.getAudioInputStream(arch));
    	         player.start();
    	      } catch (UnsupportedAudioFileException e) {
    	         e.printStackTrace();
    	 
    	      } catch (LineUnavailableException e) {
    	         e.printStackTrace();
    	      } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	 }
    
    
    public void play() {
        
        	reproduciendo = true;
			
		//	player.play();
		
    }

    public void Stop() throws Exception {
    	stop = true;
    	//player.close();
    }
    
    public boolean getStop(){
    	return stop;
    }
    
    public Clip getPlayer(){
    	return player;
    }

	public boolean isReproduciendo() {
		return reproduciendo;
	}

	public void setReproduciendo(boolean reproduciendo) {
		this.reproduciendo = reproduciendo;
	}
	
	 public boolean isBucle() {
		return bucle;
	}
	 
	 public void run(){
		 play();
	 }
}