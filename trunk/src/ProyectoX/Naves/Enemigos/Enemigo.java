package ProyectoX.Naves.Enemigos;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Explosiones.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.PowerUp;

public abstract class Enemigo extends Nave {
	protected static final URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
	
	
	protected PowerUp power; 
	
	
	public Enemigo(int vida, int vel, ImageIcon ii,int w, int h) {
		super(vida,vel, ii, new ImageIcon(explode), w, h);
		velocidadMisil = -7;
		
		
	}
	
	
	public void verificarColision() {
		if (colision(jugador)) {
			int vd = jugador.getDamageColision();
			jugador.setVida(vida);
			setVida(vd);	
		}
		
		// establece que el eneimgo no está en la pantalla, por lo que no está visible
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
		
		return new Disparo(x + width/2 , y + height, -dxAux, dyAux, velocidadMisil);
	}
	
	public Explosion getExplosion() {
		return new Explosion(x + width/2, y + height/2, new ImageIcon(explode), width, height);
	}


	public int getPuntaje() {
		return puntaje;
	}
	
	public synchronized void setVida(int vd) {
		vida -= vd;
		if(vida <= 0 && getVisible()) {
			setVisible();
			jugador.setPuntaje(puntaje);
			//si es especial agrega un powerUp al mapa
			if(isEspecial() )
				mapa.addPower(x + width/2, y + height/2, false);
		}
	}
	
	public void setPower(PowerUp power){
		this.power = power;
	}
	
	public PowerUp getPower(){
		return power;
	}
	
	public void bomba(){
		setVisible();
	}
}
