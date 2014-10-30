package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;

public class MultiplicadorLVIII extends DisparoMultiplicador{

	public MultiplicadorLVIII(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
		// TODO Auto-generated constructor stub
	}
	
	public Disparo nextLevel(){
		return new MultiplicadorLVIII(x,y,dx,dy);
	}

}
