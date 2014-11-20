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
	
	/**
	 * Constructor de la clase TorretaGrande
	 * @param xx coordenada x
	 * @param yy coordenada y
	 */
	
	public TorretaGrande(int xx, int yy) {
		super(250, new ImageIcon(url), xx, yy, 28, 55);
		
		height = 30;
	}

}
