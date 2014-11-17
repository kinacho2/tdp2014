package ProyectoX.Naves.Enemigos;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Sound.Sonido;

public abstract class Enemigo extends Nave {
	protected static final URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
	protected static final String sonido = "/ProyectoX/sounds/disparo.mp3";
	private String explodeSound = "/ProyectoX/sounds/explode2.mp3";
	
	protected PowerUp power; 
	
	protected int delay = 4;
	private int cont;
	
	
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
		
		Jugador aux = jugador.getDefensa();
		if(aux != null){
			if (colision(aux)) {
				int vd = aux.getDamageColision();
				aux.setVida(vida);
				setVida(vd);	
			}
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
		addSonidoExplosion();
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
	
	public int bomba(){
		setVisible();
		return getPuntaje();
	}
	
	protected void addSonido(){
		reproductor.addSound(sonido,false);
	}
	
	protected boolean puedeMoverse(){
		return cont % delay == 0;
	}
	
	protected void setMove(){
		cont = (cont + 1) % delay;
	}
	
	protected String getSonidoExplosion(){
		return explodeSound;
	}
	
	public boolean isInvulnerable(){
		return false;
	}
}
