package ProyectoX.PowerUps;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Laser.LaserLVI;
import ProyectoX.Disparos.Laser.LaserLVII;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Jugador.Jugador;

public class Laser extends PowerUp {

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/laser.gif"));
	
	protected String power = "l";
	
	public Laser(int x, int y) {
		super(x, y, new ImageIcon(url));
	}


	protected void efecto(Jugador jugador) {
		if(jugador.getPower().equals(power)){
			jugador.setNewDisparo(jugador.getDisparo().nextLevel());
		}
		else{
			jugador.setNewDisparo(new LaserLVI(jugador,1));
			jugador.setPower(power);
		}
		
	}
}
