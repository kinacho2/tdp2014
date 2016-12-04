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
	

	/**
	 * Constructor de la clase TorretaInvisible
	 * @param xx coordenada x
	 * @param yy coordenada y
	 */

	public TorretaInvisible(int xx, int yy) {
		super(150, new ImageIcon(url), xx, yy, 34, 47);
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
	
	//public void setVida(int vd){}
}
