package Proyecto2.Naves.Enemigos.Torretas;

import java.net.URL;

import javax.swing.ImageIcon;

import Proyecto2.Naves.Nave;

public class TorretaDoble extends Torreta{

	
	protected static final URL url = (Nave.class.getClassLoader().getResource("Proyecto2/img/Enemigo/torreta.png"));
	private static final int defaultWidth = 24;
	private static final int defaultHeight = 41;
	private static final int defaultVida = 10;
	
	
	public TorretaDoble(int xx, int yy) {
		super(defaultVida, new ImageIcon(url), xx, yy, defaultWidth, defaultHeight);
		
		height = 30;
	}

}
