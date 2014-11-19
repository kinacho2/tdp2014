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
	
	private boolean control = true;
	private boolean control2 = true;
	private int xx; 
	private int yy;
	
	public NaveAyudante(Jugador jugador){
		super(defaultVida, jugador.getVelocidad(), new ImageIcon(url), new ImageIcon(urlDer), new ImageIcon(urlIzq));
		xx = jugador.getWidth()/2 - width/2;
		yy = 50 + jugador.getHeight();
		x = jugador.getX() + xx;
		y = jugador.getY() + yy;
		height = defaultHeight;
		width = defaultWidth;
		
		setJugador(jugador);
		setMapa(jugador.getMapa());
		
		bombas = 0;
		
		setNewDisparo(new MultiplicadorLVI(x, y, dx, dy, this));
		
		setFrecuenciaDeDisparo(0,7);
	}
	
	public void setVisible(){
		super.setVisible();
		mapa.addExposion(getExplosion());
	}
	
	public void move(){
		if(control && x < jugador.getX() + jugador.getWidth() + 50 - width){
			xx++;
		}
		else{
			control = false;
			if(x > jugador.getX() - 50){
				xx--;
			}
			else{
				control = true;
			}	
		}
		if(control2 && y > jugador.getY() - 50){
			yy--;
		}
		else{
			control2 = false;
			if(y < jugador.getY() + jugador.getHeight() + 50 - height){
				yy++;
			}
			else{
				control2 = true;
			}	
		}
		x = jugador.getX() + xx;
		y = jugador.getY() + yy;
	}
	
	public void disparar(){
		if(puedeDisparar() && getVisible() && jugador.getVisible()){
			super.disparar();
		}
	}
	
}
