package ProyectoX.PowerUps;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Naves.Jugador.Defensa.Defensa;
import ProyectoX.Naves.Jugador.Defensa.EscudoX;
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
	 * @param jugador, Jugador actual
	 */
	protected void efecto(Jugador jugador) {
		Defensa[] aux = jugador.getDefensa();
		if(aux!=null && aux[0]!=null){
			int j=1;
			Defensa[] nueva = aux[0].getAyudante();
			
			jugador.setDefensa(nueva);
			
		}
		else{
			Defensa[] nueva = {new NaveAyudante(jugador,0)};
			jugador.setDefensa(nueva);
		}
		jugador.addSonidoDePremio(sound);
	}

}
