package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Jugador.Jugador;

public class PRandom extends PowerUp{

protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/random.png"));
	
	private int rn;
	
	public PRandom(int x, int y, int rn) {
		super(x, y, new ImageIcon(url));
		this.rn = rn;
	}

	@Override
	protected void efecto(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}
}
