package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVII;

public class WaveLVIII extends DisparoWave {

	public WaveLVIII(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
	}
	
	public Disparo nextLevel(){
		return new WaveLVIII(x,y,dx,dy);
	}
	
	
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[6];
		
		d[0] = new WaveLVIII(x  - 10 , y, 2, -1);
		d[1] = new WaveLVIII(x  + 10 , y, -2, -1);
		d[2] = new DisparoWave(x  - 10 , y, 2, 1);
		d[3] = new DisparoWave(x  + 10 , y, -2, 1);
		d[4] = new WaveLVIII(x  - 10 , y, 2, 1);
		d[5] = new WaveLVIII(x  + 10 , y, -2, 1);
		setearReproductor(d);
		return d;
	}

	public void move(){
		y = (int)(y + dy*(velocidad + Math.sin(variacion) * velocidad)) ;
		x = x + (int)(dx*variacion)/4 + (int)(dx *Math.sin(variacion));
		variacion = variacion + 1;
		verificarColisionBorde();
	}
}
