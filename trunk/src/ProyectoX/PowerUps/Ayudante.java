package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;


public class Ayudante extends PowerUp {

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/ayudante.png"));
	
	
	public Ayudante(int x, int y) {
		super(x, y, new ImageIcon(url));
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void efecto(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

}
