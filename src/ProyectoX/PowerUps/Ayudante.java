package ProyectoX.PowerUps;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Naves.Jugador.Defensa.Defensa;
import ProyectoX.Naves.Jugador.Defensa.NaveAyudante;

/**
 * PowerUP que le setea al Jugador una instancias de NaveAyudante
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Ayudante extends PowerUp {

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/ayudante.png"));
	
	/**
	 * Constructor de la clase Ayudante
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	
	public Ayudante(int x, int y) {
		super(x, y, new ImageIcon(url));
	}


	/**
	 * define efecto(Jugador jugador) de la clase PowerUP
	 * le setea al jugador una instancia de NaveAyudante
	 */
	protected void efecto(Jugador jugador) {
		Defensa aux = jugador.getDefensa();
		if(aux != null)
			jugador.setDefensa(jugador.getDefensa().getAyudante());
		else
			jugador.setDefensa(new NaveAyudante(jugador));
		jugador.addSonidoDePremio(sound);
	}

}
