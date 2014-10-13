package Proyecto2;

import java.net.URL;
import javax.swing.ImageIcon;

public class Normal extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/normal.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/normalDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/normalIzq.gif"));
	private static final int defaultVel = 4;
	private static final int defaultVida = 5;

	public Normal(){
		super(defaultVida,defaultVel,400,560,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
	}
}

