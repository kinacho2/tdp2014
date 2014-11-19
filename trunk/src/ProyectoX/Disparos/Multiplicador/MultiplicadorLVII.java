package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

public class MultiplicadorLVII extends DisparoMultiplicador{

	
	/**
	 * Constructor de la clase MultiplicadorLVII
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public MultiplicadorLVII(int x, int y, double dx, double dy, Nave nave) {
		super(x, y, dx, dy, nave);
	}
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de MultiplicadorLVIII que simboliza el siguiente nivel de MultiplicadorLVII
	 * @return instancia de Disparo de tipo dinamico MultiplicadorLVIII
	 */

	public DisparoJugador nextLevel(){
		return new MultiplicadorLVIII(x,y,dx,dy,nave);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 3 elementos
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[3];
		double n = 0;
		int direccion = 1;
		d[0] = new Disparo(x, y, 0, dy, velocidad);
		d[1] = new Disparo(x, y, 0.4d, dy, velocidad);
		d[2] = new Disparo(x, y, -0.4d, dy, velocidad);
		setearReproductor(d);
		return d;
	}
}
