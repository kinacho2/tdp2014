package Proyecto2;

import java.net.URL;

public class Normal extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/normal.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/normalDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/normalIzq.gif"));
	
	
	public Normal(){
		super(url,urlDer,urlIzq);
	}
}
