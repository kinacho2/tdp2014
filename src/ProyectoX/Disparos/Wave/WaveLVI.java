package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVII;

public class WaveLVI extends DisparoWave{

	
	/**
	 * Constructor de la clase WaveLVI
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public WaveLVI(int x, int y, int dx, int dy) {
		super(x, y, dx, dy);
		
	}
	
	/**
	 * redefine nextLevel() de la clase Disparo
	 * retorna una instancia de WaveLVII que simboliza el siguiente nivel de WaveLVI
	 * @return instancia de Disparo de tipo dinamico WaveLVII
	 */
	
	public Disparo nextLevel(){
		return new WaveLVII(x,y,dx,dy);
	}
	

	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 2 elementos de tipo dinamico DisparoWave
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[2];
		
		d[0] = new DisparoWave(x  - 10 , y, 2, 1);
		d[1] = new DisparoWave(x  + 10 , y, -2, 1);
	
		setearReproductor(d);
		return d;
	}

}
