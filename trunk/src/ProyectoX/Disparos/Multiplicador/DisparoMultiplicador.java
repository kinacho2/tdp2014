package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;

public abstract class DisparoMultiplicador extends Disparo {

	private static final String sound = "/ProyectoX/sounds/mul.mp3";
			
	/**
	 * Constructor de la clase DisparoMultiplicador
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public DisparoMultiplicador(int x, int y,double dx, double dy) {
		super(x, y, dx, dy, 20);
	}
	
}
