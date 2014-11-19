package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Naves.Jugador.Defensa.Defensa;
import ProyectoX.Naves.Jugador.Defensa.NaveAyudante;


public class Ayudante extends PowerUp {

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/ayudante.png"));
	
	
	public Ayudante(int x, int y) {
		super(x, y, new ImageIcon(url));
	}


	@Override
	protected void efecto(Jugador jugador) {
		Defensa aux = jugador.getDefensa();
		if(aux != null)
			jugador.setDefensa(jugador.getDefensa().getAyudante());
		else
			jugador.setDefensa(new NaveAyudante(jugador));
	}

}