package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Naves.Jugador.Defensa.Defensa;
import ProyectoX.Naves.Jugador.Defensa.EscudoX;

/**
 * PowerUP que le setea una Defensa tipo EscudoX al Jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Escudo extends PowerUp {

protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/escudo.png"));
	
	/**
	 * constructor de la clase Escudo
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	public Escudo(int x, int y) {
		super(x, y, new ImageIcon(url));
	}
	
	/**
	 * define efecto(Jugador jugador) de la clase PowerUP
	 * crea y le setea al Jugador una instancia de la clase EscudoX
	 * @param jugador, Jugador actual
	 */

	protected void efecto(Jugador jugador) {
		Defensa aux = jugador.getDefensa();
		if(aux != null)
			jugador.setDefensa(jugador.getDefensa().getEscudo());
			
		else
			jugador.setDefensa(new EscudoX(jugador));
		jugador.addSonidoDePremio(sound);
	}
}
