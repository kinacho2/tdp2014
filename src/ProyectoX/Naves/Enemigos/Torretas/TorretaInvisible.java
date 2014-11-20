package ProyectoX.Naves.Enemigos.Torretas;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Laser.LaserLVI;
import ProyectoX.Naves.Nave;

/**
 * Esta clase es una instancia de Torreta con una imagen sin fondo
 * se utilizan para simular una torreta ya montada en la imagen del Jefe
 * crean instancias de LaserLVI y disparan cada cierto tiempo hacia abajo
 * tienen mmenos vida que las demas
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class TorretaInvisible extends Torreta{
	
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/invisible.png"));
	

	private static final int defaultWidth1 = 34;
	private static final int defaultHeigth1 = 47;
	private static final int defaultVida = 150;

	/**
	 * Constructor de la clase TorretaInvisible
	 * @param xx coordenada x
	 * @param yy coordenada y
	 */

	public TorretaInvisible(int xx, int yy) {
		super(defaultVida, new ImageIcon(url), xx, yy, defaultWidth1, defaultHeigth1);
		setFrecuenciaDeDisparo(10,150);
	}
	
	/**
	 * redefine la operacion disparar() de la clase Torreta
	 * crea una instancia de LaserLVI y la agrega al mapa
	 */
	
	public void disparar() {
		if(puedeDisparar() && y < 600 && y > -width) {
			Disparo d = new LaserLVI(this,0);	
			d.setReproductor(reproductor);
			mapa.addDisparoEnemigo(d);
			
		}
	}
}
