package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVII;

public class WaveLVI extends DisparoWave{

	public WaveLVI(int x, int y, int dx, int dy) {
		super(x, y, dx, dy);
		
	}
	
	public Disparo nextLevel(){
		return new WaveLVII(x,y,dx,dy);
	}
	
	
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[2];
		
		d[0] = new DisparoWave(x  - 10 , y, 2, 1);
		d[1] = new DisparoWave(x  + 10 , y, -2, 1);
	
		
		return d;
	}

}
