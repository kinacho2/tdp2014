package ProyectoX.Disparos.Multiplicador;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Tercer nivel de DisparoMultiplicador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class MultiplicadorLVIII extends DisparoMultiplicador{

	private static final String sound = "/ProyectoX/sounds/mul.mp3";
		
	/**
	 * Constructor de la clase MultiplicadorLVIII
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public MultiplicadorLVIII(int x, int y, double dx, double dy, Nave nave) {
		super(x, y, dx, dy, nave);
		sonido = sound;
	}
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia si mismo
	 * @return instancia de Disparo de tipo dinamico MultiplicadorLVIII
	 */
	
	public DisparoJugador nextLevel(){
		return new MultiplicadorLVIII(x,y,dx,dy,nave);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 5 elementos
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[9];
		d[0] = new Disparo(x, y, 0, dy, velocidad);
		d[1] = new Disparo(x, y, 0.4d, dy, velocidad);
		d[2] = new Disparo(x, y, -0.4d, dy, velocidad);
		d[3] = new Disparo(x, y + 30, 0.4d, -dy, velocidad);
		d[4] = new Disparo(x, y + 30, -0.4d, -dy, velocidad);
		d[5] = new Disparo(x, y, 0.4d, -0.1d, velocidad*3/2);
		d[6] = new Disparo(x, y, -0.4d, -0.1d, velocidad*3/2);
		d[7] = new Disparo(x, y, 0.4d, 0.1d, velocidad*3/2);
		d[8] = new Disparo(x, y, -0.4d, 0.1d, velocidad*3/2);
		setearReproductor(d);
		return d;
	}

}
