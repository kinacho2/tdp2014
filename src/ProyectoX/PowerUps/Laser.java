package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * PowerUP que le setea al Jugador una nueva instancia de DisparoLaser
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Laser extends PowerUp {

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/laser.gif"));
	
	/**
	 * constructor de la clase Laser
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	public Laser(int x, int y) {
		super(x, y, new ImageIcon(url));
	}

	/**
	 * define efecto(Jugador jugador) de la clase PowerUP
	 * Le setea al Jugador una nueva instancia de DisparoLaser
	 * en caso de ya tener una se setea su siguiente nivel
	 * @param jugador, Jugador actual
	 */

	protected void efecto(Jugador jugador) {
		
		DisparoJugador dis = jugador.getDisparo();
		jugador.setNewDisparo(dis.getLaser());
		jugador.addSonidoDePremio(sound);
		
	}
}
