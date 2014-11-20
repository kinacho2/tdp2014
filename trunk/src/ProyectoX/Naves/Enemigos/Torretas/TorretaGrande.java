package ProyectoX.Naves.Enemigos.Torretas;

import java.net.URL;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Nave;

/**
 * Esta clase es una instancia de Torreta con una imagen de torreta grande y pesada, tienen mas vida que las demas y son mas grandes
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class TorretaGrande extends Torreta{

	
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/grande.png"));
	private static final int defaultWidth = 28;
	private static final int defaultHeight = 55;
	private static final int defaultVida = 250;
	
	/**
	 * Constructor de la clase TorretaGrande
	 * @param xx coordenada x
	 * @param yy coordenada y
	 */
	
	public TorretaGrande(int xx, int yy) {
		super(defaultVida, new ImageIcon(url), xx, yy, defaultWidth, defaultHeight);
		
		height = 30;
	}

}
