package ProyectoX.Naves.Enemigos.Torretas;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.Laser.LaserLVI;
import ProyectoX.Naves.Nave;

public class TorretaInvisible extends Torreta{
	
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/invisible.png"));
	

	private static final int defaultWidth1 = 34;
	private static final int defaultHeigth1 = 47;
	private static final int defaultVida = 150;


	public TorretaInvisible(int xx, int yy) {
		super(defaultVida, new ImageIcon(url), xx, yy, defaultWidth1, defaultHeigth1);
		setFrecuenciaDeDisparo(10,150);
	}
	
	public void disparar() {
		if(puedeDisparar() && y < 600 && y > -width) {
			Disparo d = new LaserLVI(this,0);	
			d.setReproductor(reproductor);
			mapa.addDisparoEnemigo(d);
			
		}
	}
}
