package ProyectoX.PowerUps;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * PowerUP que agrega una bomba al Jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Bomba extends PowerUp{

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/bomba.png"));

	/**
	 * Constructor de la clase Bomba
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	public Bomba(int x, int y) {
		super(x, y, new ImageIcon(url));
		// TODO Auto-generated constructor stub
	}

	/**
	 * define efecto(Jugador jugador) de la clase PowerUP
	 * aumenta la cantidad de bombas del jugador en 1
	 */
	protected void efecto(Jugador jugador) {
		jugador.setBomba();
		jugador.addSonidoDePremio(sound);
	}
	
}
