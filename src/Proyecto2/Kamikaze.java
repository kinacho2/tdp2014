package Proyecto2;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Kamikaze extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/kamikaze.png"));
	protected static final URL urlUp = (Nave.class.getClassLoader().getResource("img/Enemigo/kamikazeUp.png"));

	private static final int defaultWidth = 40;
	private static final int defaultHeight = 32;
	private static final int defaultVel = 15;
	private static final int defaultVida = 3;
	private boolean up;
	
	public Kamikaze(boolean up) {
		super(defaultVida,defaultVel,0,-defaultHeight,up? new ImageIcon(urlUp): new ImageIcon(url),defaultWidth,defaultHeight);
		Random rand = new Random();
		x = rand.nextInt(800);
		setFrecuenciaDeDisparo(7,20);
		this.up = up;
		puntaje = 35;
	}
	
	public synchronized void move() {
		
		if (y < 300) {
			dx = jugador.getX() - x;
			dy = jugador.getY() - y;
			double mod = Math.sqrt(dy*dy+dx*dx);
			setRotacion();
			
			//centra la direccion del Enemigo hacia el jugador
			dx += jugador.getWidth() / 2;
			dy += jugador.getHeight() / 2;
			
			if (dx != 0 || dy != 0) {
				dx = dx / mod;
				dy = dy / mod;
			} else {
				dy = 0.1d;
				dx = 0.1d;
			}
			y += dy * velocidad;
			x += dx * velocidad;
		} else {
			y += dy * velocidad;
			x += dx * velocidad;
		}
		verificarColision();
	}
	
	public void disparar() {
		if(puedeDisparar() && y < 350) {
			Disparo d = apuntarYDisparar();
			mapa.addDisparoEnemigo(d);
		}
	}

	public boolean isEspecial() {
		return up;
	}
}

