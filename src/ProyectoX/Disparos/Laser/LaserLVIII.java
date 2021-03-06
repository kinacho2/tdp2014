package ProyectoX.Disparos.Laser;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

/**
 * Tercer nivel del DisparLaser 
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class LaserLVIII extends DisparoLaser{

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser3.gif");
	private static final URL urlCarga = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1_carga.gif");
	
	
	/**
	 * Constructor de la clase LaserLVIII
	 * @param nave Nave que efectua el DisparoLaser
	 * @param dy direccion del laser
	 */
	
	public LaserLVIII(Nave nave, double dy) {
		super(dy, 30, 800, new ImageIcon(urlCarga), new ImageIcon(url), nave);

		x = nave.getX() + nave.getWidth()/2 - width/2;
		y = nave.getY() - height;
		damage = 8;
		
		setDelays(1000, 2000);
	}

	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de si mismo
	 * @return instancia de Disparo de tipo dinamico LaserLVIII
	 */
	
	public DisparoJugador nextLevel(){
		return new LaserLVIV(nave, dy);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * clona el tipo disparo y lo devuelve en un arreglo de disparos
	 * @return Disparo[] con 1 elemento de tipo dinamico LaserLVIII
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] toRet = new Disparo[1];
		toRet[0] = new LaserLVIII(nave, dy);
		toRet[0].setReproductor(rep);
		return toRet;
	}
	
	public int getLevel() {
		return 3;
	}
}
