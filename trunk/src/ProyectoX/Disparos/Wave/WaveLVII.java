package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVII;
import ProyectoX.Naves.Nave;

public class WaveLVII extends DisparoWave{

	
	/**
	 * Constructor de la clase WaveLVII
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public WaveLVII(int x, int y, double dx, double dy,Nave nave) {
		super(x, y, dx, dy, nave);
		
	}

	/**
	 * redefine nextLevel() de la clase Disparo
	 * retorna una instancia de WaveLVIII que simboliza el siguiente nivel de WaveLVII
	 * @return instancia de Disparo de tipo dinamico WaveLVIII
	 */
	
	public DisparoJugador nextLevel(){
		return new WaveLVIII(x,y,dx,dy,nave);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 4 elementos de tipo dinamico DisparoWave
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[4];
		
		d[0] = new DisparoWave(x  - 10 , y, 3, 1,nave);
		d[1] = new DisparoWave(x  + 10 , y, -3, 1,nave);
		d[2] = new DisparoWave(x  - 10 , y, 2, 1,nave);
		d[3] = new DisparoWave(x  + 10 , y, -2, 1,nave);
		setearReproductor(d);
		return d;
	}
}
