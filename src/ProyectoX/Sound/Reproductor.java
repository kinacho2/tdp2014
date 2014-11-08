package ProyectoX.Sound;

import java.io.File;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Reproductor extends Thread{
	
	
    private Player player;
    boolean bucle;

    public Reproductor(String sl, boolean bucle) {
    	try {
    		InputStream arch = this.getClass().getResourceAsStream(sl);
        
			player = new Player(arch);
			this.start();
			
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
public void coge(String y){

}
    public void play() {
        try {
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
    }

    public void Pausa() {
       // player.wait(1000);
    }

    public void Continuar() throws Exception {
      //  player.resume();
    }

    public void Stop() throws Exception {
       // player.stop();
    }
    
    public void run(){
    	play();
    }
}