package ProyectoX.PowerUps;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * PowerUP le aumenta la vida al Jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Vida extends PowerUp{

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/vida.png"));
	
	/**
	 * constructor de la claseVida
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	
	public Vida(int x, int y) {
		super(x, y, new ImageIcon(url));
	}


	/**
	 * define efecto(Jugador jugador) de la clase PowerUP
	 * sube la vida del jugador en 30
	 * @param jugador, Jugador actual
	 */
	protected void efecto(Jugador jugador) {
		jugador.setPotas();
		jugador.addSonidoDePremio(sound);
	}
}
