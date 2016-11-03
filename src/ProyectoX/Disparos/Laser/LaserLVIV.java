package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

public class LaserLVIV extends DisparoLaser{
	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser3.gif");
	private static final URL url2 = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser3-180.gif");
	private static final URL urlCarga = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1_carga.gif");
	
	
	/**
	 * Constructor de la clase LaserLVIII
	 * @param nave Nave que efectua el DisparoLaser
	 * @param dy direccion del laser
	 */
	
	public LaserLVIV(Nave nave, double dy) {
		super(dy, 30, 800, new ImageIcon(urlCarga), (dy==1)?new ImageIcon(url):new ImageIcon(url2), nave);
		
		x = nave.getX() + nave.getWidth()/2 - width/2;
		y = nave.getY() - height;
		damage = 8;
		
		setDelays(500, 2000);
	}

	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de si mismo
	 * @return instancia de Disparo de tipo dinamico LaserLVIII
	 */
	
	public DisparoJugador nextLevel(){
		return new LaserLVV(nave, dy,0);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * clona el tipo disparo y lo devuelve en un arreglo de disparos
	 * @return Disparo[] con 1 elemento de tipo dinamico LaserLVIII
	 */
	
	public Disparo[] cloneNivel(){
		long ret = System.currentTimeMillis();
		if(ret - init > totalDuracion || primerDisparo){
			if(primerDisparo){
				primerDisparo = false;
			}
			init = System.currentTimeMillis();
			Disparo[] toRet = new Disparo[2];
			toRet[0] = new LaserLVIV(nave, dy);
			toRet[1] = new LaserLVIV(nave, 0);
			toRet[0].setReproductor(rep);
			toRet[1].setReproductor(rep);
			return toRet;
		}
		else return new Disparo[0];
	}
}
