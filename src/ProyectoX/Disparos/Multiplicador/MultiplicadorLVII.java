package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Segundo nivel de DisparoMultiplicador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

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
		Disparo[] d = new Disparo[5];
		d[0] = new DisparoJugador(x - width/4, y, 0, dy, velocidad,null);
		d[1] = new DisparoJugador(x - width/4, y, 0.4d, dy, velocidad,null);
		d[2] = new DisparoJugador(x - width/4, y, -0.4d, dy, velocidad,null);
		d[3] = new DisparoJugador(x - width/4, y + 30, 0.4d, -dy, velocidad,null);
		d[4] = new DisparoJugador(x - width/4, y + 30, -0.4d, -dy, velocidad,null);
		setearReproductor(d);
		return d;
	}
}
