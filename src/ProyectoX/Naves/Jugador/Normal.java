package ProyectoX.Naves.Jugador;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;


public class Normal extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normal.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normalDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normalIzq.gif"));
	private static final int defaultVel = 3;
	private static final int defaultVida = 50;

	public Normal(){
		super(defaultVida,defaultVel,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
		
		
	}
	
	public static String getEstadisticas(){
		ImageIcon icon = new ImageIcon(url);
		
		return ("<html>VIDA: "+defaultVida+"<br>VELOCIDAD: "+defaultVel+"<br>POTENCIA INICIAL: "+1+"<br>ALTO: "+icon.getIconHeight()+"<br>ANCHO: "+icon.getIconWidth()+"</html>");
	}


	public void reset() {
		super.reset();
		vida = defaultVida;
		setNewDisparo(new Disparo(x + width/2 , y, 0, 1, velocidadMisil));
	}
	
}

