package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;

public abstract class DisparoMultiplicador extends Disparo {

	private static final String sound = "/ProyectoX/sounds/mul.mp3";
			
	public DisparoMultiplicador(int x, int y,double dx, double dy) {
		super(x, y, dx, dy, 20);
		//sonido = sound;
	}
	
}
