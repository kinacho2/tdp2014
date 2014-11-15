package ProyectoX.Naves.Enemigos.Torretas;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Nave;

public class TorretaGrande extends Torreta{

	
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/grande.png"));
	private static final int defaultWidth = 28;
	private static final int defaultHeight = 55;
	private static final int defaultVida = 250;
	
	
	public TorretaGrande(int xx, int yy) {
		super(defaultVida, new ImageIcon(url), xx, yy, defaultWidth, defaultHeight);
		
		height = 30;
	}

}
