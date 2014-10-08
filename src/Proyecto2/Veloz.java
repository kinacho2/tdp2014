package Proyecto2;

import java.net.URL;

public class Veloz extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/veloz.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/velozDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/velozIzq.gif"));
	
	
	public Veloz(){
		super(url,urlDer,urlIzq);
	}
}
