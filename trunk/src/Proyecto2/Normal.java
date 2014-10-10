package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Normal extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/normal.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/normalDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/normalIzq.gif"));
	
	
	public Normal(){
		super(3,400,560,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
	}
}
