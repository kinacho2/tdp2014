package Proyecto2.Naves.Enemigos;

import java.net.URL;

import javax.swing.ImageIcon;

import Proyecto2.Explosiones_Disparos.Disparo;
import Proyecto2.Explosiones_Disparos.Explosion;
import Proyecto2.Naves.Nave;
import Proyecto2.Naves.Jugador.Jugador;

public abstract class Enemigo extends Nave {
	protected static final URL explode = (Nave.class.getClassLoader().getResource("Proyecto2/img/Explosiones/nave.gif"));
	
	public Enemigo(int vida, int vel, ImageIcon ii,int w, int h) {
		super(vida,vel, ii, new ImageIcon(explode), w, h);
		velocidadMisil = -5;
		
		
	}
	
	
	public void verificarColision() {
		if (colision(jugador)) {
			int vd = jugador.getDamageColision();
			jugador.setVida(vida);
			setVida(vd);	
		}
		
		// establece que el eneimgo no est� en la pantalla, por lo que no est� visible
		if(fueraDePantalla()) {
			setVisible();
		}
		
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public abstract void disparar(); 
	
	public abstract boolean isEspecial();
	
	// devuelve un disparo direccionado al jugador
	protected Disparo apuntarYDisparar() {
		
		double dxAux=jugador.getX() - x + jugador.getWidth()/2;
		double dyAux=jugador.getY() - y + jugador.getHeight();
		double mod = Math.sqrt(dyAux*dyAux+dxAux*dxAux);
		
		if(dxAux != 0 || dyAux != 0) {
			dxAux = dxAux / mod;
			dyAux = dyAux / mod;
		} else {
			dyAux = 0.1d;
			dxAux = 0.1d;
		}
		
		return new Disparo(x + width/2 , y + height, -dxAux, dyAux, -4);
	}
	
	public Explosion getExplosion() {
		return new Explosion(x + width/2, y + height/2, new ImageIcon(explode), width, height);
	}


	public int getPuntaje() {
		return puntaje;
	}
	
	public void setVida(int vd) {
		vida -= vd;
		if(vida <= 0) {
			setVisible();
			jugador.setPuntaje(puntaje);
			//si es especial aumenta la vida del jugador
			if(isEspecial())
				jugador.setVida(-2);
		}
	}
	
}
