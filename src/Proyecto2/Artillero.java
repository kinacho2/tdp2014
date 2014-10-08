package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Artillero extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/artillero.png"));
	
	
	public Artillero(){
		super(new ImageIcon(url),60,48);
	}
	
}
