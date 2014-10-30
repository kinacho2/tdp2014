package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Laser.LaserLVII;

public class MultiplicadorLVI extends DisparoMultiplicador{

	public MultiplicadorLVI(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
		// TODO Auto-generated constructor stub
	}
	public Disparo nextLevel(){
		return new MultiplicadorLVII(x,y,dx,dy);
	}

}

