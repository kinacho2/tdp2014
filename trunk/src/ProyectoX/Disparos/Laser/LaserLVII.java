package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Jugador.Jugador;

public class LaserLVII extends DisparoLaser{
	
	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser2.gif");
	

	public LaserLVII(Jugador jugador) {
		super(1, 1, new ImageIcon(url),jugador);
		height = 800;
		width = 26;
		x = jugador.getX() + jugador.getWidth()/2 - width/2;
		y = jugador.getY() - height;
	}
	
	public Disparo nextLevel(){
		return new LaserLVIII(jugador);
	}
	

}
