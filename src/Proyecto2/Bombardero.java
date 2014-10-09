package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Bombardero extends Enemigo {
	
	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/bombardero.png"));

	public Bombardero(){
		super(new ImageIcon(url),192,168);
	}
}
