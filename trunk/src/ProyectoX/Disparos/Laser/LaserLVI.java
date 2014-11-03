package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Jugador.Jugador;

public class LaserLVI extends DisparoLaser{


	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1.gif");
	
	public LaserLVI(Jugador jugador) {
		super(40, 800, new ImageIcon(url), jugador);
		
		x = jugador.getX() + jugador.getWidth()/2 - width/2;
		y = jugador.getY() - height;
					
	}
	
	public Disparo nextLevel(){
		return new LaserLVII(jugador);
	}
	
	public Disparo[] cloneNivel(){
		Disparo[] toRet = new Disparo[1];
		toRet[0] = new LaserLVI(jugador);
		return toRet;
	}
	
}
