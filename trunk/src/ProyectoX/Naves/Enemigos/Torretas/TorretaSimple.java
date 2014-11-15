package ProyectoX.Naves.Enemigos.Torretas;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Nave;

public class TorretaSimple extends Torreta{

	
	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/simple.png"));
	private static final int defaultWidth = 26;
	private static final int defaultHeight = 49;
	private static final int defaultVida = 130;
	
	
	public TorretaSimple(int xx, int yy) {
		super(defaultVida, new ImageIcon(url), xx, yy, defaultWidth, defaultHeight);
		
		height = 30;
	} 
	
	

}
