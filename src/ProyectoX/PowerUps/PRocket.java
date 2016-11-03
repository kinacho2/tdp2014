package ProyectoX.PowerUps;

import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;


/**
 * PowerUP que le setea aleatoriamente un efecto al Jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class PRocket extends PowerUp{

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/random.png"));
	
	/**
	 * constructor de la clase PRandom
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	
	public PRocket(int x, int y) {
		super(x, y, new ImageIcon(url));
	}

	/**
	 * crea un PowerUP random y aplica su efecto al Jugador
	 * @param jugador instancia actual de Jugador
	 */
	
	protected void efecto(Jugador jugador) {
		
		jugador.setRocket();
		jugador.addSonidoDePremio(sound);
	}
}
