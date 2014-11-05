package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Jugador.Jugador;

public class LaserLVII extends DisparoLaser{
	
	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser2.gif");
	private static final URL urlCarga = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1_carga.gif");
	

	public LaserLVII(Jugador jugador) {
		super(26, 800, new ImageIcon(urlCarga), new ImageIcon(url), jugador);
		
		x = jugador.getX() + jugador.getWidth()/2 - width/2;
		y = jugador.getY() - height;
		
		damage = 5;
		
		setDelays(250, 1499, 5);
	}
	
	public Disparo nextLevel(){
		return new LaserLVIII(jugador);
	}
	
	public Disparo[] cloneNivel(){
		Disparo[] toRet = new Disparo[1];
		toRet[0] = new LaserLVII(jugador);
		return toRet;
	}
	
}
