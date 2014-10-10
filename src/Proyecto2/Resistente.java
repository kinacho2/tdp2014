package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Resistente extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/resistente.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/resistenteDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/resistenteIzq.gif"));
	
	
	public Resistente(){
		super(2,400,560,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
	}
}
