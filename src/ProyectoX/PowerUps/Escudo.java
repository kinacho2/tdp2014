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
private String escudo = "/ProyectoX/sounds/escudo.mp3";
	
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
		Defensa[] aux = jugador.getDefensa();
		//jugador.addSonidoDePremio(sound);
		if(aux!=null && aux[0] != null){
			Defensa[] nueva = {jugador.getDefensa()[0].getEscudo()};
			jugador.setDefensa(nueva);
			
		}
		else{
			Defensa[] nueva = {new EscudoX(jugador)};
			jugador.setDefensa(nueva);
			
		}
		jugador.addSonidoDePremio(escudo);
	}
}
