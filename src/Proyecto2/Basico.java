package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Basico extends Enemigo{

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/basico.png"));
	
	
	public Basico(){
		super(new ImageIcon(url),27,38);
	}
}
