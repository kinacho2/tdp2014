package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

public abstract class DisparoMultiplicador extends DisparoJugador {

	private static final String sound = "/ProyectoX/sounds/mul.mp3";
			
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
	
	public DisparoJugador getMultiplicador(){
		return nextLevel();
	}
}
