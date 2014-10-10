package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Veloz extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/veloz.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/velozDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/velozIzq.gif"));
	
	
	public Veloz(){
		super(5,400,560,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
	}
}
