package Proyecto2;

import java.net.URL;

public class Veloz extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/Balanceado.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/BalanceadoDerecha.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/BalanceadoIzquierda.gif"));
	
	
	public Veloz(){
		super(url,urlDer,urlIzq);
	}
}
