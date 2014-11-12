package ProyectoX.Naves.Jugador.Defensa;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Jugador.Jugador;

public class NaveAyudante extends Defensa {
	
	protected static URL url = (Defensa.class.getClassLoader().getResource("ProyectoX/img/PUP/Ayudante/Compa.gif"));
	protected static URL urlDer = (Defensa.class.getClassLoader().getResource("ProyectoX/img/PUP/Ayudante/CompaDer.gif"));
	protected static URL urlIzq = (Defensa.class.getClassLoader().getResource("ProyectoX/img/PUP/Ayudante/CompaIzq.gif"));
	private static final int defaultVida = 80;
	protected static final int defaultWidth = 32;
	protected static final int defaultHeight = 32;
	
	public NaveAyudante(Jugador jugador){
		super(defaultVida, jugador.getVelocidad(), new ImageIcon(url), new ImageIcon(urlDer), new ImageIcon(urlIzq));
		
		x = jugador.getX() - 50;
		y = jugador.getY() + 20;
		height = defaultHeight;
		width = defaultWidth;
		
		setJugador(jugador);
		setMapa(jugador.getMapa());
		
		bombas = 0;
		
		setNewDisparo(new MultiplicadorLVI(x, y, dx, dy));
		
		setFrecuenciaDeDisparo(0,7);
	}
	
	public void setVisible(){
		super.setVisible();
		mapa.addExposion(getExplosion());
	}
	
	public void move(){
		x = jugador.getX() - 50;
		y = jugador.getY() + 20;
	}
	
	public void disparar(){
		if(puedeDisparar() && getVisible() && jugador.getVisible()){
			super.disparar();
		}
	}

	@Override
	public void remplazo(String s) {
		//no realiza ninguna accion
	}
	
}
