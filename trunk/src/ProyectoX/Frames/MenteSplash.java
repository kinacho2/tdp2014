package ProyectoX.Frames;

import java.net.URL;

import ProyectoX.Aplication;

public class MenteSplash extends Thread{

	private SplashScreen splash;
	private Aplication api;
	private int select;
	
	public MenteSplash(int duracion, URL url, Aplication api, int select){
		splash = new SplashScreen(duracion, url);
		this.api = api;
		this.select = select;
	}
	
	public void run(){
		splash.showSplash();
		api.initGame(select);
	}

	public void esperar(int duracion) {
		long init = System.currentTimeMillis();
		while(System.currentTimeMillis() - init < duracion){
			
		}
		
	}
}
