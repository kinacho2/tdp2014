package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Basico extends Enemigo{

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/basico.png"));
	private static final int defaultWidth = 27;
	private static final int defaultHeight = 38;
	private static final int defaultVel = 5;
	
	public Basico(){
		super(5,45,600-defaultWidth,new ImageIcon(url),defaultWidth,defaultHeight);	}
}
