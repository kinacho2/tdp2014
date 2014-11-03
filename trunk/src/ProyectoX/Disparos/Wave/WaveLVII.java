package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVII;

public class WaveLVII extends DisparoWave{

	public WaveLVII(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
	}

	
	public Disparo nextLevel(){
		return new WaveLVIII(x,y,dx,dy);
	}
	
	
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[4];
		
		d[0] = new DisparoWave(x  - 10 , y, 3, 1);
		d[1] = new DisparoWave(x  + 10 , y, -3, 1);
		d[2] = new DisparoWave(x  - 10 , y, 2, 1);
		d[3] = new DisparoWave(x  + 10 , y, -2, 1);
		
		return d;
	}
}
