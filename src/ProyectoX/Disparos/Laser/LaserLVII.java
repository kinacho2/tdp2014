package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

public class LaserLVII extends DisparoLaser{
	
	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser2.gif");
	private static final URL urlCarga = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1_carga.gif");
	

	/**
	 * Constructor de la clase LaserLVII
	 * @param nave Nave que efectua el DisparoLaser
	 * @param dy direccion del laser
	 */
	
	public LaserLVII(Nave nave, double dy) {
		super(dy,22, 800, new ImageIcon(urlCarga), new ImageIcon(url), nave);
		
		x = nave.getX() + nave.getWidth()/2 - width/2;
		y = nave.getY() - height;
		
		damage = 5;
		
		setDelays(500, 1499, 5);
	}
	
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de LaserLVIII que simboliza el siguiente nivel de LaserLVII
	 * @return instancia de Disparo de tipo dinamico LaserLVIII
	 */
	
	
	public DisparoJugador nextLevel(){
		return new LaserLVIII(nave, dy);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * clona el tipo disparo y lo devuelve en un arreglo de disparos
	 * @return Disparo[] con 1 elemento de tipo dinamico LaserLVII
	 */
	
	public Disparo[] cloneNivel(){
		Disparo[] toRet = new Disparo[1];
		toRet[0] = new LaserLVII(nave,dy);
		toRet[0].setReproductor(rep);
		return toRet;
	}
	
}
