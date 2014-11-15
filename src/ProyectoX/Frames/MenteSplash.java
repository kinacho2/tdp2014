package ProyectoX.Frames;

import java.net.URL;

import ProyectoX.Aplication;
import ProyectoX.Paneles.AbstractPanel;

public class MenteSplash extends Thread{

	private SplashScreen splash;
	private Aplication api;
	private int select;
	private AbstractPanel panel;
	
	public MenteSplash(int duracion, URL url, Aplication api, int select){
		splash = new SplashScreen(duracion, url);
		this.api = api;
		this.select = select;
		panel = null;
	}
	
	public MenteSplash(int duracion, URL url, AbstractPanel panel, int select){
		splash = new SplashScreen(duracion, url);
		this.select = select;
		this.panel = panel;
	}
	
	public void run(){
		splash.showSplash();
		if(panel != null)
			panel.function(select);
		else
			api.initGame(select);
	}

}
