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
		return new MultiplicadorLVIV(x,y,dx,dy,nave,0);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 5 elementos
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] d = new Disparo[9];
		d[0] = new DisparoJugador(x - width/4, y, 0, dy, velocidad,null);
		d[1] = new DisparoJugador(x - width/4, y, 0.4d, dy, velocidad,null);
		d[2] = new DisparoJugador(x - width/4, y, -0.4d, dy, velocidad,null);
		d[3] = new DisparoJugador(x - width/4, y + 30, 0.4d, -dy, velocidad,null);
		d[4] = new DisparoJugador(x - width/4, y + 30, -0.4d, -dy, velocidad,null);
		d[5] = new DisparoJugador(x, y, 0.4d, -0.1d, velocidad*3/2,null);
		d[6] = new DisparoJugador(x, y, -0.4d, -0.1d, velocidad*3/2,null);
		d[7] = new DisparoJugador(x, y, 0.4d, 0.1d, velocidad*3/2,null);
		d[8] = new DisparoJugador(x, y, -0.4d, 0.1d, velocidad*3/2,null);
		setearReproductor(d);
		return d;
	}

}
