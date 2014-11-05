package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;

public class MultiplicadorLVII extends DisparoMultiplicador{

	public MultiplicadorLVII(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
		// TODO Auto-generated constructor stub
	}

	public Disparo nextLevel(){
		return new MultiplicadorLVIII(x,y,dx,dy);
	}
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[3];
		double n = 0;
		int direccion = 1;
		d[0] = new Disparo(x, y, 0, dy, velocidad);
		d[1] = new Disparo(x, y, 0.4d, dy, velocidad);
		d[2] = new Disparo(x, y, -0.4d, dy, velocidad);
		return d;
	}
}