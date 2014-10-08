package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Kamikaze extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/kamikaze.png"));

	
	public Kamikaze(){
		super(new ImageIcon(url),40,32);
	}
}
