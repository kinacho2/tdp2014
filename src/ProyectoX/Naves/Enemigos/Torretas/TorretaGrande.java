package ProyectoX.Naves.Enemigos.Torretas;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Rocket;

/**
 * Esta clase es una instancia de Torreta con una imagen de torreta grande y pesada, tienen mas vida que las demas y son mas grandes
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class TorretaGrande extends Torreta{

	
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/grande.png"));
	
	/**
	 * Constructor de la clase TorretaGrande
	 * @param xx coordenada x
	 * @param yy coordenada y
	 */
	private static final String sound = "/ProyectoX/sounds/mul.mp3";
	
	public TorretaGrande(int xx, int yy) {
		super(250, new ImageIcon(url), xx, yy, 28, 55);
		Random rn = new Random();
		setFrecuenciaDeDisparo(rn.nextInt(70) , 70);
		height = 30;
	}
	
	public void disparar() {
		if(puedeDisparar() && y < 600 && y > -width*3) {
			Rocket r = new Rocket(20,7);
			r.setJugador(jugador);
			r.setPosition((int)(getX() + width/2 + (width/2)*Math.sin(rotacion)), (int)(getY() -height/2 - (height/2)*Math.cos(rotacion)));
			r.addReproductor(reproductor);
			r.setVelocidad(7);
			mapa.setEnemies(r);
			reproductor.addSound(sound,false);
		}
	}
	
	
	
	public void setVida(int vd) {
		vida -= vd;
		if(vida <= 0 && getVisible()) {
			setVisible();
			if(jugador!=null)
			jugador.setPuntaje(puntaje);
			//si es especial agrega un powerUp al mapa
			mapa.addPower(400, -10, false);
		}
	}

}
