package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Jugador.Jugador;

public class LaserLVIII extends DisparoLaser{

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser2.gif");
	
	
	public LaserLVIII(Jugador jugador) {
		super(1, 1, new ImageIcon(url),jugador);
		// TODO Auto-generated constructor stub
	}

	public Disparo nextLevel(){
		return new LaserLVIII(jugador);
	}
	
}
