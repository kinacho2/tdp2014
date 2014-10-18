package Proyecto2.Naves.Jugador;

import java.net.URL;

import javax.swing.ImageIcon;

import Proyecto2.Naves.Nave;

public class Veloz extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("Proyecto2/img/Jugador/veloz.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("Proyecto2/img/Jugador/velozDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("Proyecto2/img/Jugador/velozIzq.gif"));
	private static final int defaultVel = 5;
	private static final int defaultVida = 2;
	
	
	public Veloz(){
		super(defaultVida,defaultVel,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
		x = 400;
		y = 450;
	}
	
	public static String getEstadisticas(){
		ImageIcon icon = new ImageIcon(url);
		return ("<html>VIDA: "+defaultVida+"<br>VELOCIDAD: "+defaultVel+"<br>POTENCIA INICIAL: "+1+"<br>ALTO: "+icon.getIconHeight()+"<br>ANCHO: "+icon.getIconWidth()+"</html>");
	}
}
