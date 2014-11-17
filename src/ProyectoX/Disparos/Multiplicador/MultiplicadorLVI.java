package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Laser.LaserLVII;

public class MultiplicadorLVI extends DisparoMultiplicador{

	/**
	 * Constructor de la clase MultiplicadorLVI
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public MultiplicadorLVI(int x, int y, double dx, double dy) {
		super(x, y, dx, dy);
	}
	
	/**
	 * redefine nextLevel() de la clase Disparo
	 * retorna una instancia de MultiplicadorLVII que simboliza el siguiente nivel de MultiplicadorLVI
	 * @return instancia de Disparo de tipo dinamico MultiplicadorLVII
	 */
	
	public Disparo nextLevel(){
		return new MultiplicadorLVII(x,y,dx,dy);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 2 elementos
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[2];
		
		d[0] = new Disparo(x  - 10 , y, 0, 1, velocidad);
		d[1] = new Disparo(x  + 10 , y, 0, 1, velocidad);
		
		setearReproductor(d);
		
		return d;
	}

}

