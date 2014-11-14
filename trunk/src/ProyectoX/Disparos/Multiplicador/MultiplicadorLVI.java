package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Laser.LaserLVII;

public class MultiplicadorLVI extends DisparoMultiplicador{

	public MultiplicadorLVI(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
	}
	public Disparo nextLevel(){
		return new MultiplicadorLVII(x,y,dx,dy);
	}
	
	
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[2];
		
		d[0] = new Disparo(x  - 10 , y, 0, 1, velocidad);
		d[1] = new Disparo(x  + 10 , y, 0, 1, velocidad);
		
		setearReproductor(d);
		
		return d;
	}

}

