package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Naves.Jugador.Defensa.Defensa;
import ProyectoX.Naves.Jugador.Defensa.EscudoX;
import ProyectoX.Naves.Jugador.Defensa.NaveAyudante;

public class Escudo extends PowerUp {

protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/escudo.png"));
	
	
	public Escudo(int x, int y) {
		super(x, y, new ImageIcon(url));
	}

	protected void efecto(Jugador jugador) {
		Defensa aux = jugador.getDefensa();
		if(aux != null)
			jugador.setDefensa(jugador.getDefensa().getEscudo());
		else
			jugador.setDefensa(new EscudoX(jugador));
		jugador.addSonidoDePremio(sound);
	}
}
