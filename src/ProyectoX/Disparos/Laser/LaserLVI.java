package ProyectoX.Disparos.Laser;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Primer nivel del DisparLaser 
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class LaserLVI extends DisparoLaser{


	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1.gif");
	private static final URL urlCarga = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1_carga.gif");
	
	/**
	 * Constructor de la clase LaserLVI
	 * @param nave Nave que efectua el DisparoLaser
	 * @param dy direccion del laser
	 */
	
	public LaserLVI(Nave nave, double dy) {
		super(dy, 13, 800, new ImageIcon(urlCarga), new ImageIcon(url), nave);
		
		x = nave.getX() + nave.getWidth()/2 - width/2;
		y = nave.getY() - (int)dy*height;
		
		damage = 3;
		
		setDelays(200, 1199);
	}
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de LaserLVII que simboliza el siguiente nivel de LaserLVI
	 * @return instancia de Disparo de tipo dinamico LaserLVII
	 */
	
	public DisparoJugador nextLevel(){
		return new LaserLVII(nave, dy);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * clona el tipo disparo y lo devuelve en un arreglo de disparos
	 * @return Disparo[] con 1 elemento de tipo dinamico LaserLVI
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] toRet = new Disparo[1];
		toRet[0] = new LaserLVI(nave,dy);
		toRet[0].setReproductor(rep);
		return toRet;
	}
	
}
