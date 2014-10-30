package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;

public class MultiplicadorLVIII extends DisparoMultiplicador{

	public MultiplicadorLVIII(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
	}
	
	public Disparo nextLevel(){
		return new MultiplicadorLVIII(x,y,dx,dy);
	}
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[5];
		double n = 0;
		int direccion = 1;
		d[0] = new Disparo(x, y, 0, dy, velocidad);
		d[1] = new Disparo(x, y, 0.4d, dy, velocidad);
		d[2] = new Disparo(x, y, -0.4d, dy, velocidad);
		d[3] = new Disparo(x, y + 30, 0.4d, -dy, velocidad);
		d[4] = new Disparo(x, y + 30, -0.4d, -dy, velocidad);
		return d;
	}

}
