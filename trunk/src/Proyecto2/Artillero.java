package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Artillero extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/artillero.png"));
	private static final int defaultWidth = 60;
	private static final int defaultHeight = 48;
	private static final int defaultVel = 5;
	private static final int defaultVida = 1;
	
	public Artillero(){
		super(defaultVida,defaultVel,45,600-defaultWidth,new ImageIcon(url),defaultWidth,defaultHeight);
	}
	
}
