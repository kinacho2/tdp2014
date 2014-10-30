package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;


public class DisparoWave extends Disparo {

	
	protected int variacion = 0;
	public DisparoWave(int x, int y, double dx, double dy) {
		super(x, y, dx, dy, -10);
	}
	
	public void move(){
		y = y + (int)(dy*velocidad);
		x = x + (int)(dx * Math.sin(variacion) * velocidad);
		variacion = variacion + 1;
		verificarColisionBorde();
	}
	

}
