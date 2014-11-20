package ProyectoX.Naves.Enemigos;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;

/**
 * Kamikaze es un Enemigo que aparece aleatoriamente desde arriba
 * y va moviendose rapidamente hacia el Jugador mientras dispara hacia el
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Kamikaze extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/kamikaze.png"));
	protected static final URL urlUp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/kamikazeUP.png"));

	private boolean up;
	
	/**
	 * Constructor de la clase Kamikaze
	 * @param up indica si la instancia es especial
	 */
	
	public Kamikaze(boolean up) {
		super(30, 10, up? new ImageIcon(urlUp): new ImageIcon(url), 40, 32);
		y = -defaultHeight;
		Random rand = new Random();
		x = rand.nextInt(800);
		setFrecuenciaDeDisparo(7,80);
		this.up = up;
		puntaje = 35;
	}
	
	public synchronized void move() {
		if(puedeMoverse()){
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
		}
		setMove();
		verificarColision();
	}
	
	public void disparar() {
		if(puedeDisparar() && y < 350) {
			Disparo d = apuntarYDisparar();
			d.setReproductor(reproductor);
			addSonido();
			mapa.addDisparoEnemigo(d);
		}
	}

	public boolean isEspecial() {
		return up;
	}
}

