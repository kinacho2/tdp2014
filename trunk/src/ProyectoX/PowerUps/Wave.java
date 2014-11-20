package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * PowerUP que le setea al Jugador una nueva instancia de DisparoWave
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Wave extends PowerUp {
	

protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/wave.png"));

	/**
	 * constructor de la claseVida
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	
	public Wave(int x, int y) {
		super(x, y, new ImageIcon(url));
	}

	/**
	 * define efecto(Jugador jugador) de la clase PowerUP
	 * Le setea al Jugador una nueva instancia de DisparoWave
	 * en caso de ya tener una se setea su siguiente nivel
	 * @param jugador, Jugador actual
	 */

	protected void efecto(Jugador jugador) {
		DisparoJugador dis = jugador.getDisparo();
		jugador.setNewDisparo(dis.getWave());
		jugador.addSonidoDePremio(sound);
	}
}
