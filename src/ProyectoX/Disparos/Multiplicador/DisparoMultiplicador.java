package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Clase que multiplica la cantidad de Disparos
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class DisparoMultiplicador extends DisparoJugador {
		
	/**
	 * Constructor de la clase DisparoMultiplicador
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public DisparoMultiplicador(int x, int y,double dx, double dy,Nave nave) {
		super(x, y, dx, dy, 20, nave);
	}
	
	/**
	 * redefine getMultiplicador() de la clase DisparoJugador
	 * retorna el siguiente nivel de la instancia
	 */
	public DisparoJugador getMultiplicador(){
		return nextLevel();
	}
}
