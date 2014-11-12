package ProyectoX.Naves.Jugador.Defensa;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Jugador.Jugador;

public class EscudoX extends Defensa {

	protected static final URL url = (Jugador.class.getClassLoader().getResource("ProyectoX/img/PUP/Escudo/Escudo.gif"));
	protected static final URL urlWarning = (Jugador.class.getClassLoader().getResource("ProyectoX/img/PUP/Escudo/Escudo_Warning.gif"));

	protected static final int defaultVida = 200;
	protected static final int defaultWidth = 64;
	protected static final int defaultHeight = 64;
	private boolean warning = false;
	
	public EscudoX(Jugador jugador){
		super(defaultVida, jugador.getVelocidad(),  new ImageIcon(url), new ImageIcon(url), new ImageIcon(url));
		
		x = jugador.getX() + jugador.getWidth()/2 - defaultWidth/2;
		y = jugador.getY() + jugador.getHeight()/2 - defaultHeight/2;
		height = defaultHeight;
		width = defaultWidth;
		
		
		setJugador(jugador);
		
		bombas = 0;
		
		setMapa(jugador.getMapa());
		
		ArrayList enemigos = mapa.getEnemies();
		
		for(int i = 0; i < enemigos.size(); i++){
			Enemigo m = (Enemigo) enemigos.get(i);
			m.setJugador(jugador);
		}
		setIndex(mapa.getJugador().size());
		mapa.setNewJugador(this);
		mapa.setJugador(this);
		mapa.removeJugador(jugador);
	}
	
	public void setVisible(){
		super.setVisible();
		ArrayList enemigos = mapa.getEnemies();
		
		for(int i = 0; i < enemigos.size(); i++){
			Enemigo m = (Enemigo) enemigos.get(i);
			m.setJugador(jugador);
		}
		
		resetJugador();
		
		mapa.removeJugador(this);
	}

	public void move(){
		x = jugador.getX() + jugador.getWidth()/2 - defaultWidth/2;
		y = jugador.getY() + jugador.getHeight()/2 - defaultHeight/2;
	}
	
	public void disparar(){
		
	}
	
	public void remplazo() {
		super.remplazo();
		resetJugador();
	}
	
	private void resetJugador(){
		mapa.setJugador(jugador);
		mapa.setNewJugador(jugador);
	}
	
	public void setVida(int vd){
		super.setVida(vd);	
		
		if(vida <= 50) 
			setWarning();
		
	}
	
	private void setWarning(){
		if(!warning){
			icon = new ImageIcon(urlWarning);
			iconDer = new ImageIcon(urlWarning);
			iconIzq = new ImageIcon(urlWarning);
			warning = true;
		}
	}
}
