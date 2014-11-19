package ProyectoX.Disparos;

import ProyectoX.Disparos.Laser.LaserLVI;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Disparos.Wave.WaveLVI;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * Clase creada para implementar el patron State
 * con los diferentes tipos de Disparo
 * Esta clase no se declara abstracta debido a que los Disparos simples 
 * son instancias propias de ella que implementa el mismo comportamiento 
 * que su antesesor Disparo con el agregado del patron State
 */
public class DisparoJugador extends Disparo{

	
	protected Nave nave;
	
	/**
	 * constructor de la clase DisparoJugador
	 * @param x coordenada x
	 * @param y coordenada y 
	 * @param dx diferencial x
	 * @param dy diferencial y
	 * @param missileSpeed cantidad de pixeles que se mueve por iteracion
	 * @param nave Nave que tiene como arma la instancia
	 */
	
	public DisparoJugador(int x, int y, double dx, double dy, int missileSpeed, Nave nave) {
		super(x, y, dx, dy, missileSpeed);
		this.nave = nave;
	}
	
	/**
	 * Retorna el siguiente nivel del disparo 
	 * en esta clase base es el mismo disparo
	 * @return instancia de DisparoJugador
	 */
	
	public DisparoJugador nextLevel(){
		return new DisparoJugador(x,y,dx,dy,velocidad,nave);
	}
	
	/**
	 * retorna una instancia de tipo dinamico LaserLVI
	 * @return instancia de LaserLVI
	 */
	
	public DisparoJugador getLaser(){
		return new LaserLVI(nave,1);
	}
	
	/**
	 * retorna una instancia de tipo dinamico MultiplicadorLVI
	 * @return instancia de MultiplicadorLVI
	 */
	
	public DisparoJugador getMultiplicador(){
		return new MultiplicadorLVI(x,y,dx,dy,nave);
	}
	
	/**
	 * retorna una instancia de tipo dinamico WaveLVI
	 * @return instancia de WaveLVI
	 */
	
	public DisparoJugador getWave(){
		return new WaveLVI(x,y,dx,dy,nave);
	}

}
