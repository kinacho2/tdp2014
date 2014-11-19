package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Jugador.Jugador;

public class Multiplicador extends PowerUp{

protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/multiplicador.gif"));
	protected String power = "mul";
	
	public Multiplicador(int x, int y) {
		super(x, y, new ImageIcon(url));
	}


	protected void efecto(Jugador jugador) {
			
		DisparoJugador dis = jugador.getDisparo();
		jugador.setNewDisparo(dis.getMultiplicador());
			
		
		
	}
}
