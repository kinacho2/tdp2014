package ProyectoX.Naves.Enemigos.Torretas;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Nave;

/**
 * Esta clase es una instancia de Torreta con una imagen de torreta doble, tienen menos vida que las demas y son mas pequenias
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class TorretaDoble extends Torreta{

	
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/torreta.png"));
	private static final int defaultWidth = 24;
	private static final int defaultHeight = 41;
	private static final int defaultVida = 100;
	
	/**
	 * Constructor de la clase TorretaDoble
	 * @param xx coordenada x
	 * @param yy coordenada y
	 */
	
	public TorretaDoble(int xx, int yy) {
		super(defaultVida, new ImageIcon(url), xx, yy, defaultWidth, defaultHeight);
		
		height = 30;
	}

}
