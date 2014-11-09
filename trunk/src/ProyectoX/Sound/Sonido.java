package ProyectoX.Sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class Sonido extends Thread {
	
	protected String file;
	
	public Sonido( String file ) {
		this.file = file;
		this.start();
	}

	@Override
	public void run() {
		try{
			InputStream fis = this.getClass().getResourceAsStream(file);
			Player playMP3 = new Player(fis);
		    playMP3.play();
		}
		catch(Exception ex)
		{  
			ex.printStackTrace();
		}
	}

}
