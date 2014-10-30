package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;

public class LaserLVI extends DisparoLaser{


	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1.gif");
	
	public LaserLVI(int x, int y) {
		super(x, y, new ImageIcon(url));
		// TODO Auto-generated constructor stub
	}
	
	public Disparo nextLevel(){
		return new LaserLVII(x,y);
	}
	
}
