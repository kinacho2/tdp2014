package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Normal extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("Proyecto2/img/Jugador/normal.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("Proyecto2/img/Jugador/normalDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("Proyecto2/img/Jugador/normalIzq.gif"));
	private static final int defaultVel = 3;
	private static final int defaultVida = 5;

	public Normal(){
		super(defaultVida,defaultVel,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
		x = 400;
		y = 450;
		
	}
	
	public static String getEstadisticas(){
		ImageIcon icon = new ImageIcon(url);
		
		return ("<html>VIDA: "+defaultVida+"<br>VELOCIDAD: "+defaultVel+"<br>POTENCIA INICIAL: "+1+"<br>ALTO: "+icon.getIconHeight()+"<br>ANCHO: "+icon.getIconWidth()+"</html>");
	}
	
}

