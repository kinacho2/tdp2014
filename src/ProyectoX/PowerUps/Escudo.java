package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;

public class Escudo extends PowerUp {

protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/escudo.png"));
	
	
	public Escudo(int x, int y) {
		super(x, y, new ImageIcon(url));
	}


	@Override
	protected void efecto(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}
}
