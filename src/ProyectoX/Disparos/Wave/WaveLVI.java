package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Primer nivel de DisparoWave
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class WaveLVI extends DisparoWave{

	
	/**
	 * Constructor de la clase WaveLVI
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public WaveLVI(int x, int y, double dx, double dy,Nave nave) {
		super(x, y, dx, dy, nave);
		
	}
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de WaveLVII que simboliza el siguiente nivel de WaveLVI
	 * @return instancia de Disparo de tipo dinamico WaveLVII
	 */
	
	public DisparoJugador nextLevel(){
		return new WaveLVII(x,y,dx,dy,nave);
	}
	

	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 2 elementos de tipo dinamico DisparoWave
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[2];
		
		d[0] = new DisparoWave(x  - 10 , y, 2, 1,nave);
		d[1] = new DisparoWave(x  + 10 , y, -2, 1,nave);
	
		
		setearReproductor(d);
		return d;
	}
	
	public void move(){
		y = y + (int)(dy * Math.sin(variacion)*velocidad);
		x = x + (int)(dx * velocidad);
		
		variacion = variacion + 1;
		verificarColisionBorde();
	}

}
