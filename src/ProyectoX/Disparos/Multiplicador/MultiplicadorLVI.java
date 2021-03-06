package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Primer nivel de DisparoMultiplicador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class MultiplicadorLVI extends DisparoMultiplicador{

	/**
	 * Constructor de la clase MultiplicadorLVI
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public MultiplicadorLVI(int x, int y, double dx, double dy, Nave nave) {
		super(x, y, dx, dy, nave);
	}
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de MultiplicadorLVII que simboliza el siguiente nivel de MultiplicadorLVI
	 * @return instancia de Disparo de tipo dinamico MultiplicadorLVII
	 */
	
	public DisparoJugador nextLevel(){
		return new MultiplicadorLVII(x,y,dx,dy,nave);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 2 elementos
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[2];
		
		d[0] = new DisparoJugador(x  - 10 - width/2 , y, 0, 1, velocidad,null);
		d[1] = new DisparoJugador(x  + 10 , y, 0, 1, velocidad,null);
		
		setearReproductor(d);
		
		return d;
	}

}

